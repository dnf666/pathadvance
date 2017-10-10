!(function () {

  var TRACTOR_TOUCHING = 'tractor-touching';
  var TRACTOR_LESS = 'tractor-less';
  var TRACTOR_GREATER = 'tractor-greater';
  var TRACTOR_REFRESHING = 'tractor-refreshing';

  var constructorFunc = function () { };

  var Tractor = function (options) {
    // 下拉容器偏移值
    this.translate = 0;

    // 是否已经触发滚动加载状态
    this.scrollerLoading = false;

    var defaults = {
      scroller: 'body', // 滚动容器
      openDragLoading: true, // 开启下拉加载
      openScrollLoading: true, // 开启滚动加载
      dragValve: 40, // 下拉加载阀值
      scrollValve: 40, // 滚动加载阀值
      onDragStart: constructorFunc, // 下拉开始
      onDragLessValve: constructorFunc, // 下拉中，但还没到刷新阀值
      onDragGreaterValve: constructorFunc, // 下拉中，已经达到刷新阀值
      onDragDone: constructorFunc, // 下拉结束
      onScroll2Valve: constructorFunc // 滚动到阀值
    }

    this.tractor = extend(defaults, options || {});
    this.tractor.scroller = document.querySelector(this.tractor.scroller);

    if (this.tractor.openDragLoading) { this.initDrag(); }
    if (this.tractor.openScrollLoading) { this.initScroll(); }
  };

  Tractor.prototype.initDrag = function () {
    var self = this;
    var tractor = this.tractor;
    var isTouchStart = false; // 是否已经触发下拉条件
    var isDragStart = false; // 是否已经开始下拉
    var valveState = false; // 是否下拉到阈值，用来触发 hook 的标识

    // 下拉 touchstart 时的点坐标
    var startX, startY;

    // 监听下拉
    tractor.scroller.addEventListener('touchstart', touchStart, false);
    tractor.scroller.addEventListener('touchmove', touchMove, false);
    tractor.scroller.addEventListener('touchend', touchEnd, false);

    function touchStart(event) {
      // 只有当容器视图处于最顶部的时候才能触发下拉事件
      if (tractor.scroller.scrollTop <= 0) {
        isTouchStart = true;
        startX = event.changedTouches[0].pageX;
        startY = event.changedTouches[0].pageY;
      }
    }

    function touchMove(event) {
      // Tips:
      // return false 会阻止默认事件

      if (!isTouchStart) { return; }

      // 手指在屏幕移动的距离
      var distance = event.changedTouches[0].pageY - startY;
      if (distance > 0) {
        // 下拉时容器偏移的距离
        self.translate = Math.pow(event.changedTouches[0].pageY - startY, 0.85);
      } else {
        // 为了避免多次给元素设置样式属性
        if (self.translate !== 0) {
          self.translate = 0;
          elTransform(tractor.scroller, 'translate3d(0, ' + self.translate + 'px, 0)');
        }
      }

      // 避免横向滑屏
      // var diffDistance = Math.abs(event.touches[0].pageX - startX) - Math.abs(event.touches[0].pageY - startY);
      // if (diffDistance > 0) return false;

      if (distance > 0) {
        event.preventDefault();

        tractor.scroller.classList.add(TRACTOR_TOUCHING);

        // 触发下拉开始 hook
        if (!isDragStart) {
          isDragStart = true;

          // hook
          tractor.onDragStart();
        }

        if (self.translate <= tractor.dragValve) {
          // 容器偏移值未达到下拉加载（刷新）阈值

          if (tractor.scroller.classList.contains(TRACTOR_GREATER)) { tractor.scroller.classList.remove(TRACTOR_GREATER); }
          if (!tractor.scroller.classList.contains(TRACTOR_LESS)) { tractor.scroller.classList.add(TRACTOR_LESS); }

          // 触发下拉未达到阈值状态 hook
          if (!valveState) {
            valveState = !valveState;
            tractor.onDragLessValve();
          }
        } else {
          // 容器偏移值已达到下拉加载（刷新）阈值

          if (tractor.scroller.classList.contains(TRACTOR_LESS)) { tractor.scroller.classList.remove(TRACTOR_LESS); }
          if (!tractor.scroller.classList.contains(TRACTOR_GREATER)) { tractor.scroller.classList.add(TRACTOR_GREATER); }

          // 触发下拉已达到阈值状态 hook
          if (valveState) {
            valveState = !valveState;
            tractor.onDragGreaterValve();
          }
        }

        elTransform(tractor.scroller, 'translate3d(0, ' + self.translate + 'px, 0)');
      }
    }

    function touchEnd(event) {
      if (!isTouchStart) { return; }

      // 下拉结束还原状态
      isDragStart = false;
      isTouchStart = false;

      tractor.scroller.classList.remove(TRACTOR_TOUCHING);

      if (self.translate <= tractor.dragValve) {
        tractor.scroller.classList.remove(TRACTOR_LESS);
        self.translateScroller(300, 0);
      } else {
        tractor.scroller.classList.remove(TRACTOR_GREATER);
        tractor.scroller.classList.add(TRACTOR_REFRESHING);
        self.translateScroller(100, tractor.dragValve);

        // 触发下拉加载（刷新）完成 hook
        tractor.onDragDone();
      }
    }
  };

  Tractor.prototype.initScroll = function () {
    var self = this;
    var tractor = this.tractor;

    // 监听滚动
    tractor.scroller.addEventListener('scroll', scrolling, false);

    function scrolling() {
      if (self.scrollerLoading) { return; }

      var scrollerscrollHeight = tractor.scroller.scrollHeight;
      var scrollerHeight = tractor.scroller.getBoundingClientRect().height;
      var scrollerTop = tractor.scroller.scrollTop;
      var scrollValve = scrollerscrollHeight - scrollerHeight - scrollerTop;

      // 达到滚动加载阀值
      if (scrollValve <= tractor.scrollValve) {
        self.scrollerLoading = true;

        // hook
        tractor.onScroll2Valve();
      }
    }
  };

  Tractor.prototype.translateScroller = function (consuming, valve) {
    var self = this;

    requestAnimationFrame(translateRAF);

    var time = 0;
    function translateRAF(timestamp) {
      if (!time) time = timestamp;
      var remain = self.translate - self.translate * (timestamp - time) / consuming;
      if (remain < valve) remain = self.translate = valve;

      elTransform(self.tractor.scroller, 'translate3d(0, ' + remain + 'px, 0)');

      if (remain > valve) requestAnimationFrame(translateRAF);
    }
  };

  Tractor.prototype.dragLoadingDone = function () {
    this.tractor.scroller.classList.remove(TRACTOR_REFRESHING);
    this.translateScroller(300, 0);
  };

  Tractor.prototype.scrollLoadingDone = function () {
    this.scrollerLoading = false;
  };

  function extend(to, from) {
    Object.keys(from).forEach(function (key) {
      to[key] = from[key];
    });
    return to;
  }

  function elTransform(el, transform) {
    var elStyle = el.style;
    elStyle.webkitTransform = elStyle.MozTransform = elStyle.transform = transform;
  };

  window.Tractor = Tractor;

})();
