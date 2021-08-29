var ctxUrl;

var _CSS_ = {
    pointerCss: {css: {'cursor': 'pointer'}}
};

var AGE_LEVEL_MAP = {
    1: "10岁~20岁",
    2: "20岁~30岁",
    3: "30岁~40岁",
    4: "40岁~50岁",
    5: "50岁~60岁",
    6: "60岁~70岁",
    7: "70岁~80岁",
    99: "其他年龄段"
};

var GOTONE_LEVEL = {
    1: "银卡",
    2: "金卡",
    3: "白金卡",
    4: "钻石卡",
    5: "终身卡",
    6: "体验卡",
    99: "其他卡"
};

function getPortalUrlPrefix(portal_prefix) {
    var baseUrl = window.location.href;
    if (baseUrl.indexOf(portal_prefix) >= 0) {
        return "/" + portal_prefix + "/";
    } else {
        return "";
    }
}

function getCtxUrl(appName) {
    var baseUrl = window.location.href;
    if (baseUrl.indexOf(appName) === 17) {
        return appName + "/";
    } else {
        return "";
    }
}

function getFujianCityJson() {
    var cityJson = {};
    cityJson["591"] = ["鼓楼", "晋安", "台江", "仓山", "马尾", "长乐", "罗源", "平潭", "连江", "永泰", "闽清", "闽侯", "福清"];
    cityJson["592"] = ["湖里", "思明", "海沧", "集美", "同安", "翔安"];
    cityJson["593"] = ["宁德市区", "福鼎", "周宁", "屏南", "柘荣", "霞浦", "福安", "古田", "寿宁"];
    cityJson["594"] = ["莆田市区", "仙游", "涵江", "秀屿"];
    cityJson["595"] = ["鲤城", "丰泽", "洛江", "泉港", "晋江", "石狮", "南安", "惠安", "永春", "安溪", "德化"];
    cityJson["596"] = ["芗城", "华安", "龙文", "龙海", "诏安", "云霄", "长泰", "南靖", "漳浦", "平和", "东山"];
    cityJson["597"] = ["龙岩市区", "永定", "连城", "漳平", "上杭", "长汀", "武平"];
    cityJson["598"] = ["三明市区", "清流", "泰宁", "沙县", "大田", "建宁", "明溪", "宁化", "尤溪", "将乐", "永安"];
    cityJson["599"] = ["南平市区", "建阳", "光泽", "政和", "建瓯", "顺昌", "武夷山", "浦城", "松溪", "邵武"];
    return cityJson;
}

function getFujianCityArr() {
    return ["福州市", "厦门市", "宁德市", "莆田市", "泉州市", "漳州市", "龙岩市", "三明市", "南平市"];
}

function renderLaydateMonth(eleId, fmt) {
    laydate.render({
        elem: '#' + eleId,
        type: 'month',
        format: fmt
    });
}

function renderLaydateTime(eleId, fmt) {
    laydate.render({
        elem: '#' + eleId,
        type: 'time',
        format: fmt
    });
}

function renderLaydate(eleId, fmt) {
    laydate.render({
        elem: '#' + eleId,
        format: fmt
    });
}

function renderLaydatetime(eleId, fmt) {
    laydate.render({
        elem: '#' + eleId,
        type: 'datetime',
        format: fmt
    });
}

function getPoorQualityIndicatorName(zhCnName) {
    switch (zhCnName) {
        case 'ATTACH成功率':
            return "attachSucRate";
        case 'EPS成功率':
            return "epsSucRate";
        case 'DNS成功率':
            return "dnsSucRate";
        case 'TCP核心成功率':
            return "tcpCoreSucRate";
        case 'TCP无线成功率':
            return "tcpWxSucRate";
        case 'HTTP业务成功率':
            return "httpSucRate";
        case 'HTTP下载速率':
            return "httpDownRate";
        case 'ATTACH时延':
            return "attachDelay";
        case 'EPS时延':
            return "epsDelay";
        case '上行流量':
            return "upTraffic";
        case '下行流量':
            return "downTraffic";
        case '首包响应时延':
            return "firstPkgDelay";
        case 'HTTP大包速率':
            return "httpLgPkgRate";
        case 'HTTP小包时延':
            return "httpSmPkgDelay";
        default:
            return "unknown";
    }
}

function getIndicatorZhCnName(indicatorName) {
    switch (indicatorName) {
        case 'attachSucRate':
            return "ATTACH成功率(%)";
        case 'attachDelay':
            return "ATTACH时延(ms)";
        case 'epsSucRate':
            return "EPS成功率(%)";
        case 'epsDelay':
            return "EPS时延(ms)";
        case 'dnsSucRate':
            return "DNS成功率(%)";
        case 'tcpCoreSucRate':
            return "TCP核心成功率(%)";
        case 'tcpWxSucRate':
            return "TCP无线成功率(%)";
        case 'httpSucRate':
            return "HTTP业务成功率(%)";
        case 'httpDownRate':
            return "HTTP下载速率(kbps)";
        case 'upTraffic':
            return "上行流量(MB)";
        case 'downTraffic':
            return "下行流量(MB)";
        case 'firstPkgDelay':
            return "首包响应时延(ms)";
        case 'httpLgPkgRate':
            return "HTTP大包速率(kbps)";
        case 'httpSmPkgDelay':
            return "HTTP小包时延(ms)";
        default:
            return "unknown";
    }
}

function getCityCode(cityName) {
    switch (cityName) {
        case '福州':
            return 591;
        case '厦门':
            return 592;
        case '宁德':
            return 593;
        case '莆田':
            return 594;
        case '泉州':
            return 595;
        case '漳州':
            return 596;
        case '龙岩':
            return 597;
        case '三明':
            return 598;
        case '南平':
            return 599;
        default:
            return 590;
    }
}

function getCityName(cityCode, defName) {
    if (defName == null || defName === "") {
        defName = "福建";
    }
    switch (cityCode) {
        case 591:
            return '福州';
        case 592:
            return '厦门';
        case 593:
            return '宁德';
        case 594:
            return '莆田';
        case 595:
            return '泉州';
        case 596:
            return '漳州';
        case 597:
            return '龙岩';
        case 598:
            return '三明';
        case 599:
            return '南平';
        default:
            return defName;
    }
}

function doLoadBootstrapTableData(tbEle, reqParamJson) {
    tbEle.bootstrapTable('destroy');
    tbEle.bootstrapTable({
        url: reqParamJson.url,
        queryParams: function (params) {
            if (_.get(reqParamJson, "sortable", false)) {
                return _.merge({offset: params.offset, limit: params.limit, sort: params.sort, order: params.order}, reqParamJson.queryData);
            } else {
                return _.merge({offset: params.offset, limit: params.limit}, reqParamJson.queryData);
            }
        },
        showHeader: _.get(reqParamJson, "showHeader", true),
        showColumns: _.get(reqParamJson, "showColumns", false),
        sortable: _.get(reqParamJson, "sortable", false),
        sortName: _.get(reqParamJson, "sortName", undefined),
        sortOrder: _.get(reqParamJson, "sortOrder", 'asc'),
        silent: true,
        striped: true,
        pagination: reqParamJson.pagination,
        sidePagination: _.get(reqParamJson, "sidePagination", "server"),
        paginationVAlign: _.get(reqParamJson, "paginationVAlign", "bottom"),
        pageSize: _.get(reqParamJson, "pageSize", 10),
        pageList: [5, 10, 25, 50, 100],
        classes: reqParamJson.tableClasses,
        rowStyle: _.get(reqParamJson, "rowStyle", function (row, idx) {
            return {};
        }),
        columns: reqParamJson.columns
    });
}

function doLoadBootstrapTableLocalData(tbEle, reqParamJson) {
    tbEle.bootstrapTable('destroy');
    tbEle.bootstrapTable({
        data: reqParamJson.data,
        showHeader: _.get(reqParamJson, "showHeader", true),
        sortable: _.get(reqParamJson, "sortable", false),
        silent: true,
        striped: true,
        pagination: reqParamJson.pagination,
        sidePagination: _.get(reqParamJson, "sidePagination", "server"),
        paginationVAlign: _.get(reqParamJson, "paginationVAlign", "bottom"),
        pageSize: _.get(reqParamJson, "pageSize", 10),
        pageList: [5, 10, 25, 50, 100],
        classes: reqParamJson.tableClasses,
        rowStyle: _.get(reqParamJson, "rowStyle", function (row, idx) {
            return {};
        }),
        columns: reqParamJson.columns
    });
}


function loadDataAsync(paramJson) {
    var queryData = paramJson.queryData;
    $.ajax({
        type: "post",
        url: ctxUrl + paramJson.targetUrl,
        dataType: "json",
        data: queryData,
        success: function (resp) {
            var respData = eval(resp);
            paramJson.callbackFn(respData);
        }
    });
}

function doNothing(data) {
    console.log("Do nothing, just for testing:" + JSON.stringify(data));
}

function formatCityCode(value, row, index) {
    return getCityName(value);
}

function isValidNumber(value) {
    return !(_.isUndefined(value) || _.isNull(value) || _.isNaN(value));

}

function formatAgeLevel(value, row, index) {
    var tmp = 99;
    if (isValidNumber(value)) {
        tmp = _.toInteger(value);
    } else {
        return AGE_LEVEL_MAP[99];
    }
    return AGE_LEVEL_MAP[tmp];
}

function formatGotoneLevel(value, row, index) {
    var tmp = 99;
    if (isValidNumber(value)) {
        tmp = _.toInteger(value);
    } else {
        return GOTONE_LEVEL[99];
    }
    return GOTONE_LEVEL[tmp];
}

function formatToPercentage(value, row, index) {
    var tmp = 0;
    if (isValidNumber(value)) {
        tmp = _.toNumber(value) * 100;
    } else {
        return "-";
    }
    return _.round(tmp, 2) + "%";
}

function formatToTrafficSpeed(value, row, index) {
    var tmp = 0;
    if (isValidNumber(value)) {
        tmp = _.toNumber(value);
    }
    return _.round(tmp, 2) + "kbps";
}

function formatToTrafficGB(value, row, index) {
    var tmp = 0;
    if (isValidNumber(value)) {
        tmp = _.toNumber(value);
    }
    return _.round(tmp, 2) + "GB";
}

function formatToTrafficMB(value, row, index) {
    var tmp = 0;
    if (isValidNumber(value)) {
        tmp = _.toNumber(value);
    }
    return _.round(tmp, 2) + "MB";
}

function formatToDelay(value, row, index) {
    var tmp = 0;
    if (isValidNumber(value)) {
        tmp = _.toNumber(value);
    }
    return _.round(tmp, 2) + "ms";
}

function formatToInt(value, row, index) {
    var tmp = 0;
    if (isValidNumber(value)) {
        tmp = _.toNumber(value);
    } else {
        return "-";
    }
    return _.round(tmp, 0);
}

function formatToPrecision4(value, row, index) {
    var tmp = 0;
    if (isValidNumber(value)) {
        tmp = _.toNumber(value);
    } else {
        return "-";
    }
    return _.round(tmp, 4);
}

function formatToPrecision6(value, row, index) {
    var tmp = 0;
    if (isValidNumber(value)) {
        tmp = _.toNumber(value);
    } else {
        return "-";
    }
    return _.round(tmp, 6);
}

function formatProportion(value, row, index) {
    var tmp = 0;
    if (isValidNumber(value)) {
        tmp = _.toNumber(value);
    } else {
        return "-";
    }
    return _.round(tmp, 2) + "%";
}

function formatPrecision4(value, row, index) {
    var tmp = 0;
    if (isValidNumber(value)) {
        tmp = _.toNumber(value);
    } else {
        return "-";
    }
    return _.round(tmp, 4) + "%";
}

function formatBoolean(value, row, index) {
    if (!_.isBoolean(value)) {
        return "-";
    }
    return value ? '是' : '否';
}

function formatNumeric(value, row, index) {
    var tmp = 0;
    if (isValidNumber(value)) {
        tmp = _.toNumber(value);
    } else {
        return "-";
    }
    tmp = _.round(tmp, 2);
    var className = "";
    if (tmp < 0) {
        className = "color-red";
    }
    return "<span class='" + className + "'>" + tmp + "</span>";
}

function formatDissatisfiedRate2(value, row, index) {
    var tmp = 0;
    if (isValidNumber(value)) {
        tmp = _.toNumber(value);
    } else {
        return "-";
    }
    tmp = _.round(tmp, 2);
    var className = "color-green";
    if (tmp < 5) {
        className = "color-green";
    } else if (tmp >= 5 && tmp < 25) {
        className = "color-yellow";
    } else if (tmp >= 25 && tmp < 60) {
        className = "color-orange";
    } else {
        className = "color-red";
    }
    return "<span class='" + className + "'>" + tmp + "%</span>";
}

function formatLongText(value, row, index) {
    var shortName = _.truncate(value, {length: 12});
    var link = "<a href='#' title='" + value + "' disabled='true'><span class='color-white'>" + shortName + "</span></a>";
    return link;
}

function truncateLongText(value, row, index) {
    var shortName = _.truncate(value, {length: 12});
    var link = "<span title='" + value + "'  class='color-white'>" + shortName + "</span>";
    return link;
}

function formatLongToDate(value, row, index) {
    var str = "" + value;
    return str.substr(0, 4) + "-" + str.substr(4, 2) + "-" + str.substr(6, 2);
}

function formatLongToDateTime(value, row, index) {
    var str = "" + value;
    return str.substr(0, 4) + "-" + str.substr(4, 2) + "-" + str.substr(6, 2) + " "
        + str.substr(8, 2) + ":" + str.substr(10, 2);
}

function formatSucRate(value, row, index) {
    var tmp = 0;
    if (isValidNumber(value)) {
        tmp = _.toNumber(value);
    }
    tmp = _.round(tmp, 2);
    var className = "color-green";
    if (tmp >= 95) {
        className = "color-green";
    } else if (tmp >= 80 && tmp < 95) {
        className = "color-yellow";
    } else if (tmp >= 70 && tmp < 80) {
        className = "color-orange";
    } else {
        className = "color-red";
    }
    return "<span class='" + className + "'>" + tmp + "%</span>";
}

function formatSucRateX100(value, row, index) {
    var tmp = 0;
    if (isValidNumber(value)) {
        tmp = _.toNumber(value) * 100;
    }
    tmp = _.round(tmp, 2);
    var className = "color-green";
    if (tmp >= 95) {
        className = "color-green";
    } else if (tmp >= 80 && tmp < 95) {
        className = "color-yellow";
    } else if (tmp >= 70 && tmp < 80) {
        className = "color-orange";
    } else {
        className = "color-red";
    }
    return "<span class='" + className + "'>" + tmp + "%</span>";
}

function formatAlarmLevel(value, row, index) {
    var className = "color-yellow";
    var alarmName = "一级告警";
    var alarmValue = _.toNumber(value);
    if (_.isEqual(alarmValue, 3)) {
        className = "color-red";
        alarmName = "三级告警";
    } else if (_.isEqual(alarmValue, 2)) {
        className = "color-orange";
        alarmName = "二级告警";
    }
    return "<b class='" + className + "'>" + alarmName + "</b>";
}

function resizeScreen(width, height) {
    var screenWidth = $(window).width();
    var screenHeight = $(window).height();
    if (screenWidth < width) {
        screenWidth = width;
    }
    if (screenHeight < height) {
        screenHeight = height;
    }
    $(document.body).width(screenWidth);
    $(document.body).height(screenHeight);
}

function toggleDropdownMenu() {
    $('#menu_list').toggle();
}

function showDropdownMenu() {
    $('#menu_list').show();
}

function hideDropdownMenu() {
    $('#menu_list').hide();
}

function setViewportInitScale() {
    var docEl = document.documentElement;
    var domWidth = docEl.clientWidth;
    var screenWidth = window.innerWidth;
    console.log("domWidth = " + domWidth + ", screenWidth = " + screenWidth);
    var metaEl = document.querySelector('meta[name="viewport"]');
    // 动态改写meta:viewport标签
    var scale = _.round(screenWidth / 1900, 3);
    console.log("viewport initial-scale = " + scale);
    if (!metaEl) {
        metaEl = document.createElement('meta');
        metaEl.setAttribute('name', 'viewport');
        metaEl.setAttribute('content', 'width=device-width, initial-scale=' + scale + ', maximum-scale=' + scale + ', minimum-scale=' + scale);
        docEl.firstElementChild.appendChild(metaEl);
    } else {
        metaEl.setAttribute('content', 'width=device-width, initial-scale=' + scale + ', maximum-scale=' + scale + ', minimum-scale=' + scale);
    }
}

function setViewportScale(doc, win) {
    var docEl = win.document.documentElement;
    var metaEl = doc.querySelector('meta[name="viewport"]');
    var dpr = 0;
    var scale = 0;

    if (!dpr && !scale) {
        var appVersion = win.navigator.appVersion;
        console.log();
        var isAndroid = appVersion.match(/(android)/gi);
        var isIPhone = appVersion.match(/(iphone|ipad|apple)/gi);
        var devicePixelRatio = win.devicePixelRatio;

        if(isIPhone || isAndroid) {
            dpr = devicePixelRatio;
            console.log("Change dpr to:" + dpr);
        } else {
            dpr = 1;
        }

        scale = 1 / dpr;
        console.log("Change scale to:" + scale);
    }

    /**
     * ================================================
     *   设置data-dpr和viewport
     × ================================================
     */

    docEl.setAttribute('data-dpr', dpr);
    // 动态改写meta:viewport标签
    if (!metaEl) {
        metaEl = doc.createElement('meta');
        metaEl.setAttribute('name', 'viewport');
        metaEl.setAttribute('content', 'width=device-width, initial-scale=' + scale + ', maximum-scale=' + scale + ', minimum-scale=' + scale + ', user-scalable=no');
        document.documentElement.firstElementChild.appendChild(metaEl);
    } else {
        metaEl.setAttribute('content', 'width=device-width, initial-scale=' + scale + ', maximum-scale=' + scale + ', minimum-scale=' + scale + ', user-scalable=no');
    }

}

function updateSystemDate(app) {
    var targetUrl = "getSystemDate";
    if (!_.isNull(app) && _.size(app) > 0) {
        targetUrl = '/' + app + '/getSystemDate';
    }
    loadDataAsync({
        queryData: {},
        targetUrl: targetUrl,
        callbackFn: function (resp) {
            $('#system_date').text(resp.systemDate);
        }
    });
}

function schedule(taskFun, duration) {
    setInterval(function() {
        taskFun();
    }, duration);
}

function toggleCityNavTab(navEleId, cityId) {
    $('#' + navEleId + ' span').each(function () {
        if (_.toInteger($(this).attr("id")) === cityId) {
            $(this).addClass("el-active");
        } else {
            $(this).removeClass("el-active");
        }
    });
}

function getActiveCityId(eleId) {
    var cityId = _.toInteger($('#city_id').val());
    $('#' + eleId + ' span').each(function () {
        if ($(this).hasClass("el-active")) {
            cityId = _.toInteger($(this).attr("id"));
        }
    });
    return cityId;
}

function toggleIndicatorGroupNavTab(navEleId, groupId) {
    $('#' + navEleId + ' span').each(function () {
        if (_.toInteger($(this).attr("id")) === groupId) {
            $(this).addClass("el-active");
        } else {
            $(this).removeClass("el-active");
        }
    });
}

function generateDropDownMenu(menuListHtml, btnClasses) {
    if (btnClasses == null || btnClasses === undefined) {
        btnClasses = " btn-xs btn-success "
    }
    return "<div class='btn-group'>" +
        "   <button type='button' class='btn dropdown-toggle " + btnClasses + " ' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>" +
        "       <span>下钻</span>" +
        "       <span class='caret'></span>" +
        "   </button>" +
        "   <ul class='dropdown-menu' style='min-width: 60px;'>" + menuListHtml + "</ul>" +
        "</div>";
}

Date.prototype.addDays = function(days) {
    this.setDate(this.getDate() + days);
    return this;
};

Date.prototype.minusDays = function(days) {
    this.setDate(this.getDate() - days);
    return this;
};

Date.prototype.parseFromYYYYMMDD = function(dateStr) {
    var year = dateStr.substr(0, 4);
    var month = _.toInteger(dateStr.substr(4, 2)) - 1;
    var day = _.toInteger(dateStr.substr(6, 2));
    this.setFullYear(year);
    this.setMonth(month);
    this.setDate(day);
    return this;
};

Date.prototype.formatToYYYYMMDD = function() {
    var month = this.getMonth() + 1;
    if (month < 10) {
        month = "0" + month;
    }
    return this.getFullYear() + "" + month + "" + this.getDate();
};

function getViewDataCache() {
    return $('#view_data_cache');
}

function ssoAuth(ssoUrlPrefix, fromPortalFlag, targetUrl, ssoCallbackFn) {
    var ssoUrl = ssoUrlPrefix + '/user/token.json';
    console.log("sso-url = " + ssoUrl);
    var option = {
        url: ssoUrl,
        type: 'post',
        success: function (resp) {
            console.log(JSON.stringify(resp));
            var respData = eval(resp);
            var sid = respData["sid"];
            loadDataAsync({
                targetUrl: targetUrl,
                queryData: {sid: sid},
                callbackFn: ssoCallbackFn
            });
        },
        error: function (resp) {
            console.error(JSON.stringify(resp));
        }
    };
    if (fromPortalFlag === 0) {
        option.xhrFields = {withCredentials: true};
        option.crossDomain = true;
    }
    $.ajax(option);
}

