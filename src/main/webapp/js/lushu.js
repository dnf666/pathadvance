1 /**
 2  * @fileoverview 百度地图的轨迹跟随类，对外开放。
 3  * 用户可以在地图上自定义轨迹运动
 4  * 可以自定义路过某个点的图片，文字介绍等。
 5  * 主入口类是<a href="symbols/BMapLib.LuShu.html">LuShu</a>，
 6  * 基于Baidu Map API 1.2。.
 7  *
 8  * @author Baidu Map Api Group
 9  * @version 1.2
 10  */
11
12 /**
 13  * @namespace BMap的所有library类均放在BMapLib命名空间下
 14  */
15 var BMapLib = window.BMapLib = BMapLib || {};
16
17 (function() {
    18     //声明baidu包
    19     var T, baidu = T = baidu || {version: '1.5.0'};
    20     baidu.guid = '$BAIDU$';
    21     //以下方法为百度Tangram框架中的方法，请到http://tangram.baidu.com 查看文档
    22     (function() {
        23         window[baidu.guid] = window[baidu.guid] || {};
        24         baidu.dom = baidu.dom || {};
        25         baidu.dom.g = function(id) {
            26             if ('string' == typeof id || id instanceof String) {
                27                 return document.getElementById(id);
                28             } else if (id && id.nodeName && (id.nodeType == 1 || id.nodeType == 9)) {
                29                 return id;
                30             }
            31             return null;
            32         };
        33         baidu.g = baidu.G = baidu.dom.g;
        34         baidu.lang = baidu.lang || {};
        35         baidu.lang.isString = function(source) {
            36             return '[object String]' == Object.prototype.toString.call(source);
            37         };
        38         baidu.isString = baidu.lang.isString;
        39         baidu.dom._g = function(id) {
            40             if (baidu.lang.isString(id)) {
                41                 return document.getElementById(id);
                42             }
            43             return id;
            44         };
        45         baidu._g = baidu.dom._g;
        46         baidu.dom.getDocument = function(element) {
            47             element = baidu.dom.g(element);
            48             return element.nodeType == 9 ? element : element.ownerDocument || element.document;
            49         };
        50         baidu.browser = baidu.browser || {};
        51         baidu.browser.ie = baidu.ie = /msie (\d+\.\d+)/i.test(navigator.userAgent) ? (document.documentMode || + RegExp['\x241']) : undefined;
        52         baidu.dom.getComputedStyle = function(element, key) {
            53             element = baidu.dom._g(element);
            54             var doc = baidu.dom.getDocument(element),
                55                 styles;
            56             if (doc.defaultView && doc.defaultView.getComputedStyle) {
                57                 styles = doc.defaultView.getComputedStyle(element, null);
                58                 if (styles) {
                    59                     return styles[key] || styles.getPropertyValue(key);
                    60                 }
                61             }
            62             return '';
            63         };
        64         baidu.dom._styleFixer = baidu.dom._styleFixer || {};
        65         baidu.dom._styleFilter = baidu.dom._styleFilter || [];
        66         baidu.dom._styleFilter.filter = function(key, value, method) {
            67             for (var i = 0, filters = baidu.dom._styleFilter, filter; filter = filters[i]; i++) {
                68                 if (filter = filter[method]) {
                    69                     value = filter(key, value);
                    70                 }
                71             }
            72             return value;
            73         };
        74         baidu.string = baidu.string || {};
        75
        76
        77         baidu.string.toCamelCase = function(source) {
            78
            79             if (source.indexOf('-') < 0 && source.indexOf('_') < 0) {
                80                 return source;
                81             }
            82             return source.replace(/[-_][^-_]/g, function(match) {
                83                 return match.charAt(1).toUpperCase();
                84             });
            85         };
        86         baidu.dom.getStyle = function(element, key) {
            87             var dom = baidu.dom;
            88             element = dom.g(element);
            89             key = baidu.string.toCamelCase(key);
            90
            91             var value = element.style[key] ||
                92                         (element.currentStyle ? element.currentStyle[key] : '') ||
                93                         dom.getComputedStyle(element, key);
            94
            95             if (!value) {
                96                 var fixer = dom._styleFixer[key];
                97                 if (fixer) {
                    98                     value = fixer.get ? fixer.get(element) : baidu.dom.getStyle(element, fixer);
                    99                 }
                100             }
            101
            102             if (fixer = dom._styleFilter) {
                103                 value = fixer.filter(key, value, 'get');
                104             }
            105             return value;
            106         };
        107         baidu.getStyle = baidu.dom.getStyle;
        108         baidu.dom._NAME_ATTRS = (function() {
            109             var result = {
                110                 'cellpadding': 'cellPadding',
                111                 'cellspacing': 'cellSpacing',
                112                 'colspan': 'colSpan',
                113                 'rowspan': 'rowSpan',
                114                 'valign': 'vAlign',
                115                 'usemap': 'useMap',
                116                 'frameborder': 'frameBorder'
            117             };
            118
            119             if (baidu.browser.ie < 8) {
                120                 result['for'] = 'htmlFor';
                121                 result['class'] = 'className';
                122             } else {
                123                 result['htmlFor'] = 'for';
                124                 result['className'] = 'class';
                125             }
            126
            127             return result;
            128         })();
        129         baidu.dom.setAttr = function(element, key, value) {
            130             element = baidu.dom.g(element);
            131             if ('style' == key) {
                132                 element.style.cssText = value;
                133             } else {
                134                 key = baidu.dom._NAME_ATTRS[key] || key;
                135                 element.setAttribute(key, value);
                136             }
            137             return element;
            138         };
        139         baidu.setAttr = baidu.dom.setAttr;
        140         baidu.dom.setAttrs = function(element, attributes) {
            141             element = baidu.dom.g(element);
            142             for (var key in attributes) {
                143                 baidu.dom.setAttr(element, key, attributes[key]);
                144             }
            145             return element;
            146         };
        147         baidu.setAttrs = baidu.dom.setAttrs;
        148         baidu.dom.create = function(tagName, opt_attributes) {
            149             var el = document.createElement(tagName),
                150                 attributes = opt_attributes || {};
            151             return baidu.dom.setAttrs(el, attributes);
            152         };
        153         baidu.object = baidu.object || {};
        154         baidu.extend =
            155         baidu.object.extend = function(target, source) {
            156             for (var p in source) {
                157                 if (source.hasOwnProperty(p)) {
                    158                     target[p] = source[p];
                    159                 }
                160             }
            161             return target;
            162         };
        163     })();
    164
    165     /**
     166      * @exports LuShu as BMapLib.LuShu
     167      */
    168     var LuShu =
        169     /**
     170      * LuShu类的构造函数
     171      * @class LuShu <b>入口</b>。
     172      * 实例化该类后，可调用,start,end,pause等方法控制覆盖物的运动。
     173
     174      * @constructor
     175          * @param {Map} map Baidu map的实例对象.
     176          * @param {Array} path 构成路线的point的数组.
     177          * @param {Json Object} opts 可选的输入参数，非必填项。可输入选项包括：<br />
     178          * {<br />"<b>landmarkPois</b>" : {Array} 要在覆盖物移动过程中，显示的特殊点。格式如下:landmarkPois:[<br />
179          *      {lng:116.314782,lat:39.913508,html:'加油站',pauseTime:2},<br />
180          *      {lng:116.315391,lat:39.964429,html:'高速公路收费站,pauseTime:3}]<br />
181          * <br />"<b>icon</b>" : {Icon} 覆盖物的icon,
182          * <br />"<b>speed</b>" : {Number} 覆盖物移动速度，单位米/秒    <br />
183          * <br />"<b>defaultContent</b>" : {String} 覆盖物中的内容    <br />
184          * }<br />.
     185          * @example <b>参考示例：</b><br />
     186          * var lushu = new BMapLib.LuShu(map,arrPois,{defaultContent:"从北京到天津",landmarkPois:[]});
     187      */
    188      BMapLib.LuShu = function(map, path, opts) {
        189         if (!path || path.length < 1) {
            190             return;
            191         }
        192         this._map = map;
        193         //存储一条路线
        194         this._path = path;
        195         //移动到当前点的索引
        196         this.i = 0;
        197         //控制暂停后开始移动的队列的数组
        198         this._setTimeoutQuene = [];
        199         //进行坐标转换的类
        200         this._projection = this._map.getMapType().getProjection();
        201         this._opts = {
            202             icon: null,
            203             //默认速度 米/秒
        204             speed: 4000,
            205             defaultContent: ''
        206         };
        207         this._setOptions(opts);
        208
        209         //如果不是默认实例，则使用默认的icon
        210         if (!this._opts.icon instanceof BMap.Icon) {
            211             this._opts.icon = defaultIcon;
            212         }
        213     }
    214      /**
     215      * 根据用户输入的opts，修改默认参数_opts
     216      * @param {Json Object} opts 用户输入的修改参数.
     217      * @return 无返回值.
     218      */
    219     LuShu.prototype._setOptions = function(opts) {
        220         if (!opts) {
            221             return;
            222         }
        223         for (var p in opts) {
            224             if (opts.hasOwnProperty(p)) {
                225                 this._opts[p] = opts[p];
                226             }
            227         }
        228     }
    229
    230     /**
     231      * @description 开始运动
     232      * @param none
     233      * @return 无返回值.
     234      *
     235      * @example <b>参考示例：</b><br />
     236      * lushu.start();
     237      */
    238     LuShu.prototype.start = function() {
        239         var me = this,
            240             len = me._path.length;
        241         //不是第一次点击开始,并且小车还没到达终点
        242         if (me.i && me.i < len - 1) {
            243             //没按pause再按start不做处理
            244             if (!me._fromPause) {
                245                 return;
                246             }else if(!me._fromStop){
                247 	            //按了pause按钮,并且再按start，直接移动到下一点
                248 	            //并且此过程中，没有按stop按钮
                249 	            //防止先stop，再pause，然后连续不停的start的异常
                250 	            me._moveNext(++me.i);
                251             }
            252         }else {
            253             //第一次点击开始，或者点了stop之后点开始
            254             me._addMarker();
            255             //等待marker动画完毕再加载infowindow
            256             me._timeoutFlag = setTimeout(function() {
                257                     me._addInfoWin();
                258                     me._moveNext(me.i);
                259             },400);
            260         }
        261          //重置状态
        262         this._fromPause = false;
        263         this._fromStop = false;
        264     },
        265     /**
     266      * 结束运动
     267      * @return 无返回值.
     268      *
     269      * @example <b>参考示例：</b><br />
     270      * lushu.stop();
     271      */
    272     LuShu.prototype.stop = function() {
        273         this.i = 0;
        274         this._fromStop = true;
        275         clearInterval(this._intervalFlag);
        276         this._clearTimeout();
        277         //重置landmark里边的poi为未显示状态
        278         for (var i = 0, t = this._opts.landmarkPois, len = t.length; i < len; i++) {
            279             t[i].bShow = false;
            280         }
        281     };
    282     /**
     283      * 暂停运动
     284      * @return 无返回值.
     285      */
    286     LuShu.prototype.pause = function() {
        287         clearInterval(this._intervalFlag);
        288
        289         //标识是否是按过pause按钮
        290         this._fromPause = true;
        291         this._clearTimeout();
        292     };
    293     /**
     294      * 隐藏上方overlay
     295      * @return 无返回值.
     296      *
     297      * @example <b>参考示例：</b><br />
     298      * lushu.hideInfoWindow();
     299      */
    300     LuShu.prototype.hideInfoWindow = function() {
        301         this._overlay._div.style.visibility = 'hidden';
        302     };
    303     /**
     304      * 显示上方overlay
     305      * @return 无返回值.
     306      *
     307      * @example <b>参考示例：</b><br />
     308      * lushu.showInfoWindow();
     309      */
    310     LuShu.prototype.showInfoWindow = function() {
        311         this._overlay._div.style.visibility = 'visible';
        312     };
    313     //Lushu私有方法
    314     baidu.object.extend(LuShu.prototype, {
        315         /**
         316          * 添加marker到地图上
         317          * @param {Function} 回调函数.
         318          * @return 无返回值.
         319          */
            320         _addMarker: function(callback) {
            321             if (this._marker) {
                322                 this.stop();
                323                 this._map.removeOverlay(this._marker);
                324                 clearTimeout(this._timeoutFlag);
                325             }
            326             //移除之前的overlay
            327             this._overlay && this._map.removeOverlay(this._overlay);
            328             var marker = new BMap.Marker(this._path[0]);
            329             this._opts.icon && marker.setIcon(this._opts.icon);
            330             this._map.addOverlay(marker);
            331             marker.setAnimation(BMAP_ANIMATION_DROP);
            332             this._marker = marker;
            333         },
        334         /**
         335          * 添加上方overlay
         336          * @return 无返回值.
         337          */
            338         _addInfoWin: function() {
            339             var me = this;
            340             var overlay = new CustomOverlay(me._marker.getPosition(), me._opts.defaultContent);
            341
            342             //将当前类的引用传给overlay。
            343             overlay.setRelatedClass(this);
            344             this._overlay = overlay;
            345             this._map.addOverlay(overlay);
            346         },
        347         /**
         348          * 获取墨卡托坐标
         349          * @param {Point} poi 经纬度坐标.
         350          * @return 无返回值.
         351          */
            352         _getMercator: function(poi) {
            353             return this._map.getMapType().getProjection().lngLatToPoint(poi);
            354         },
        355         /**
         356          * 计算两点间的距离
         357          * @param {Point} poi 经纬度坐标A点.
         358          * @param {Point} poi 经纬度坐标B点.
         359          * @return 无返回值.
         360          */
            361         _getDistance: function(pxA, pxB) {
            362             return Math.sqrt(Math.pow(pxA.x - pxB.x, 2) + Math.pow(pxA.y - pxB.y, 2));
            363         },
        364           //目标点的  当前的步长,position,总的步长,动画效果,回调
            365         /**
         366          * 移动小车
         367          * @param {Number} poi 当前的步长.
         368          * @param {Point} initPos 经纬度坐标初始点.
         369          * @param {Point} targetPos 经纬度坐标目标点.
         370          * @param {Function} effect 缓动效果.
         371          * @return 无返回值.
         372          */
        373         _move: function(initPos,targetPos,effect) {
        374             var me = this,
            375                 //当前的帧数
        376                 currentCount = 0,
            377                 //步长，米/秒
        378                 timer = 10,
            379                 step = this._opts.speed / (1000 / timer),
            380                 //初始坐标
        381                 initPos = this._projection.lngLatToPoint(initPos),
            382                 //获取结束点的(x,y)坐标
        383                 targetPos = this._projection.lngLatToPoint(targetPos),
            384                 //总的步长
        385                 count = Math.round(me._getDistance(initPos, targetPos) / step);
        386
        387             //如果小于1直接移动到下一点
        388             if (count < 1) {
            389                 me._moveNext(++me.i);
            390                 return;
            391             }
        392             //两点之间匀速移动
        393             me._intervalFlag = setInterval(function() {
            394             //两点之间当前帧数大于总帧数的时候，则说明已经完成移动
            395 	            if (currentCount >= count) {
                396 	                clearInterval(me._intervalFlag);
                397 	                //移动的点已经超过总的长度
                398 		        	if(me.i > me._path.length){
                    399 						return;
                    400 		        	}
                401 		        	//运行下一个点
                402 	                me._moveNext(++me.i);
                403 	            }else {
                404 	                    //正在移动
                405 	                    currentCount++;
                406 	                    var x = effect(initPos.x, targetPos.x, currentCount, count),
                    407 	                        y = effect(initPos.y, targetPos.y, currentCount, count),
                    408 	                        pos = me._projection.pointToLngLat(new BMap.Pixel(x, y));
                409 	                    //设置marker
                410 	                    me._marker.setPosition(pos);
                411 	                    //设置自定义overlay的位置
                412 	                    me._setInfoWin(pos);
                413 	                }
            414 	        },timer);
        415         },
    416         /**
     417          * 移动到下一个点
     418          * @param {Number} index 当前点的索引.
     419          * @return 无返回值.
     420          */
    421         _moveNext: function(index) {
        422             var me = this;
        423             if (index < this._path.length - 1) {
            424                 me._move(me._path[index], me._path[index + 1], me._tween.linear);
            425             }
        426         },
    427         /**
     428          * 设置小车上方infowindow的内容，位置等
     429          * @param {Point} pos 经纬度坐标点.
     430          * @return 无返回值.
     431          */
    432         _setInfoWin: function(pos) {
        433             //设置上方overlay的position
        434             var me = this;
        435             me._overlay.setPosition(pos, me._marker.getIcon().size);
        436             var index = me._troughPointIndex(pos);
        437             if (index != -1) {
            438                 clearInterval(me._intervalFlag);
            439                 me._overlay.setHtml(me._opts.landmarkPois[index].html);
            440                 me._overlay.setPosition(pos, me._marker.getIcon().size);
            441                 me._pauseForView(index);
            442             }else {
            443                 me._overlay.setHtml(me._opts.defaultContent);
            444             }
        445         },
    446         /**
     447          * 在某个点暂停的时间
     448          * @param {Number} index 点的索引.
     449          * @return 无返回值.
     450          */
    451         _pauseForView: function(index) {
        452             var me = this;
        453             var t = setTimeout(function() {
            454                 //运行下一个点
            455                 me._moveNext(++me.i);
            456             },me._opts.landmarkPois[index].pauseTime * 1000);
        457             me._setTimeoutQuene.push(t);
        458         },
    459          //清除暂停后再开始运行的timeout
    460         _clearTimeout: function() {
        461             for (var i in this._setTimeoutQuene) {
            462                 clearTimeout(this._setTimeoutQuene[i]);
            463             }
        464             this._setTimeoutQuene.length = 0;
        465         },
    466          //缓动效果
    467         _tween: {
        468             //初始坐标，目标坐标，当前的步长，总的步长
        469             linear: function(initPos, targetPos, currentCount, count) {
            470                 var b = initPos, c = targetPos - initPos, t = currentCount,
                471                 d = count;
            472                 return c * t / d + b;
            473             }
        474         },
    475
    476         /**
     477          * 否经过某个点的index
     478          * @param {Point} markerPoi 当前小车的坐标点.
     479          * @return 无返回值.
     480          */
    481         _troughPointIndex: function(markerPoi) {
        482             var t = this._opts.landmarkPois, distance;
        483             for (var i = 0, len = t.length; i < len; i++) {
            484                 //landmarkPois中的点没有出现过的话
            485                 if (!t[i].bShow) {
                486                     distance = this._map.getDistance(new BMap.Point(t[i].lng, t[i].lat), markerPoi);
                487                     //两点距离小于10米，认为是同一个点
                488                     if (distance < 10) {
                    489                         t[i].bShow = true;
                    490                         return i;
                    491                     }
                492                 }
            493             }
        494            return -1;
        495         }
    496     });
    497     /**
     498      * 自定义的overlay，显示在小车的上方
     499      * @param {Point} Point 要定位的点.
     500      * @param {String} html overlay中要显示的东西.
     501      * @return 无返回值.
     502      */
    503     function CustomOverlay(point,html) {
        504         this._point = point;
        505         this._html = html;
        506     }
    507     CustomOverlay.prototype = new BMap.Overlay();
    508     CustomOverlay.prototype.initialize = function(map) {
        509         var div = this._div = baidu.dom.create('div', {style: 'border:solid 1px #ccc;width:auto;min-width:50px;text-align:center;position:absolute;background:#fff;color:#000;font-size:12px;border-radius: 10px;padding:5px;white-space: nowrap;'});
        510         div.innerHTML = this._html;
        511         map.getPanes().floatPane.appendChild(div);
        512         this._map = map;
                 return div;
             }
        CustomOverlay.prototype.draw = function() {
                 this.setPosition(this.lushuMain._marker.getPosition(), this.lushuMain._marker.getIcon().size);
             }
         baidu.object.extend(CustomOverlay.prototype, {
                 //设置overlay的position
                     setPosition: function(poi,markerSize) {
                         // 此处的bug已修复，感谢 苗冬(diligentcat@gmail.com) 的细心查看和认真指出
                         var px = this._map.pointToOverlayPixel(poi),
                                 styleW = baidu.dom.getStyle(this._div, 'width'),
                                 styleH = baidu.dom.getStyle(this._div, 'height');
                             overlayW = parseInt(this._div.clientWidth || styleW, 10),
                                overlayH = parseInt(this._div.clientHeight || styleH, 10);
                         this._div.style.left = px.x - overlayW / 2 + 'px';
                         this._div.style.bottom = -(px.y - markerSize.height) + 'px';
                     },
                 //设置overlay的内容
                     setHtml: function(html) {
                         this._div.innerHTML = html;
                     },
                 //跟customoverlay相关的实例的引用
                     setRelatedClass: function(lushuMain) {
                        this.lushuMain = lushuMain;
                     }
            });
    })();