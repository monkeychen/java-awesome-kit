var echartsColorArr = ['#ff3333','#B5C334','#FCCE10','#E87C25', '#FE8463','#9BCA63','#FAD860','#F3A43B', '#EEE021', '#60C0DD', '#0fc2b3',
    '#61A0A8', '#33bfd4', '#91C7AE', '#CA8622', '#BDA29A', '#6E7074', '#546570', '#C4CCD3', '#749F83', '#00FF7F', '#BFEFFF', '#C0FF3E', '#27727B'];

var gradientColorArr = ['#ff3333', 'orange', 'yellow','lime','aqua'];

var echartsDefaultOption = {
    color: echartsColorArr,
    grid: [{
        left: 50,
        // containLabel: true,
        width: '85%'
    }],
    title: {
        left: 'center',
        textStyle: {
            color: '#1698da'
        }
    },
    legend: {
        textStyle: {
            color: '#FFFFFF'
        },
        selectedMode: 'multiple',
        inactiveColor: '#CCC',
        data: []
    },
    axisPointer: {
        label: {
            backgroundColor: 'rgba(50,50,50,0.7)',
            color: '#5dff17'
        }
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross'
        }
    },
    xAxis: [
        {
            type: 'category',
            axisLine: {
                lineStyle: {
                    color: '#FFFFFF'
                }
            },
            axisTick: {show: false},
            axisLabel: {show: true, interval: 0, rotate: 0},
            data: []
        }
    ],
    yAxis: {}
};

var echartsShowLoadingOps = {
    text: '数据加载中...',
    color: '#c23531',
    textColor: '#FFF',
    maskColor: 'transparent',
    zlevel: 0
};

var seriesLabelConfigYellow = {
    normal: {
        show: true,
        position: 'top',
        textStyle: {
            color: '#ffff13',
            fontSize: 15
        }
    }
};

var LinearGradientLib = [
    new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#87CEFA'}, {offset: 0.5, color: '#1C86EE'}, {offset: 1, color: '#000080'}]),
    new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#CD9B9B'}, {offset: 0.5, color: '#CD5C5C'}, {offset: 1, color: '#EE2C2C'}]),
    new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#B0C4DE'}, {offset: 0.5, color: '#AB82FF'}, {offset: 1, color: '#9400D3'}]),
    new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#7CCD7C'}, {offset: 0.5, color: '#00CD00'}, {offset: 1, color: '#76EE00'}]),
    new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#6d8c6c'}, {offset: 0.5, color: '#3d8b5d'}, {offset: 1, color: '#058b5b'}]),
    new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#87CEEB'}, {offset: 0.5, color: '#7D9EC0'}, {offset: 1, color: '#7171C6'}]),
    new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#EECFA1'}, {offset: 0.5, color: '#CD9B9B'}, {offset: 1, color: '#C67171'}]),
    new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#8B636C'}, {offset: 0.5, color: '#8B3A62'}, {offset: 1, color: '#8B1C62'}]),
    new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#79CDCD'}, {offset: 0.5, color: '#3A5FCD'}, {offset: 1, color: '#104E8B'}]),
    new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#8B3A62'}, {offset: 0.5, color: '#8B0A50'}, {offset: 1, color: '#8B2323'}]),
    new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#9AC0CD'}, {offset: 0.5, color: '#8968CD'}, {offset: 1, color: '#7D26CD'}]),
    new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: '#8B7E66'}, {offset: 0.5, color: '#8B7355'}, {offset: 1, color: '#8B5F65'}]),
    new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: 'rgba(18,150,219, 0.2)'}, {offset: 0.9, color: 'rgba(18,150,219, 0)'}], false),
    new echarts.graphic.LinearGradient(0, 0, 0, 1, [{offset: 0, color: 'rgba(18,150,219, 0.3)'}, {offset: 0.9, color: 'rgba(18,150,219, 0)'}], false)
];

var seriesItemStyleArr = [
    {normal: {color: LinearGradientLib[0]}, emphasis: {color: LinearGradientLib[0]}},
    {normal: {color: LinearGradientLib[1]}, emphasis: {color: LinearGradientLib[1]}},
    {normal: {color: LinearGradientLib[2]}, emphasis: {color: LinearGradientLib[2]}},
    {normal: {color: LinearGradientLib[3]}, emphasis: {color: LinearGradientLib[3]}},
    {normal: {color: LinearGradientLib[4]}, emphasis: {color: LinearGradientLib[4]}},
    {normal: {color: LinearGradientLib[5]}, emphasis: {color: LinearGradientLib[5]}},
    {normal: {color: LinearGradientLib[6]}, emphasis: {color: LinearGradientLib[6]}},
    {normal: {color: LinearGradientLib[7]}, emphasis: {color: LinearGradientLib[7]}},
    {normal: {color: LinearGradientLib[8]}, emphasis: {color: LinearGradientLib[8]}},
    {normal: {color: LinearGradientLib[9]}, emphasis: {color: LinearGradientLib[9]}},
    {normal: {color: LinearGradientLib[10]}, emphasis: {color: LinearGradientLib[10]}},
    {normal: {color: LinearGradientLib[11]}, emphasis: {color: LinearGradientLib[11]}}
];

var lineSeriesStyle = {
    symbolSize: 10,
    lineStyle: {
        normal: {
            width: 3
            // shadowColor: 'rgba(255, 255, 255, 0.5)',
            // shadowBlur: 5
        }
    },
    areaStyle: {
        normal: {
            color: LinearGradientLib[12]
        }
    }
};

var lineSeriesStyle2 = {
    symbolSize: 20,
    lineStyle: {
        normal: {
            width: 5,
            shadowColor: 'rgba(255, 255, 255, 0.5)',
            shadowBlur: 10
        }
    },
    areaStyle: {
        normal: {
            color: LinearGradientLib[13]
        }
    }
};

function tooltipFormatter(params) {
    var title = "";
    var content = "";
    var colorConfig = "<span style='display:inline-block;margin-right:5px;border-radius:10px;width:10px;height:10px;background-color:";
    _.forEach(params, function (param) {
        var color = "white";
        if (_.isString(param.color)) {
            color = param.color;
        } else {
            if (!_.isNull(param.color) && _.isArray(param.color.colorStops)) {
                color = param.color.colorStops[1].color;
            }
        }
        title = "<h4 class='text-center color-blue'><b>" + param.name + "</b></h4>";
        content += colorConfig + color + ";'></span><span class='font-15'>"
            + param.seriesName + ": " + _.round(param.value, 2) + "</span><br>";
    });
    return title + content;
}

function initEchartInstance(domId, defOption) {
    var instance = echarts.getInstanceByDom(document.getElementById(domId));
    if (instance != null) {
        return instance;
    }
    instance = echarts.init(document.getElementById(domId));
    if (defOption != null) {
        instance.setOption(defOption);
    }
    return instance;
}

function getEchartInstance(domId) {
    return echarts.getInstanceByDom(document.getElementById(domId));
}

function disposeEchartInstance(domId) {
    var instance = echarts.getInstanceByDom(document.getElementById(domId));
    if (instance != null) {
        echarts.dispose(instance);
    }
}

function initEchartInstanceAndShow(domId, defOption) {
    var instance = echarts.getInstanceByDom(document.getElementById(domId));
    if (instance != null) {
        instance.showLoading(echartsShowLoadingOps);
        return instance;
    }
    instance = echarts.init(document.getElementById(domId));
    if (defOption != null) {
        instance.setOption(defOption);
    }
    instance.showLoading(echartsShowLoadingOps);
    return instance;
}

function setChartOption(echartInstance, chartOption) {
    echartInstance.hideLoading();
    echartInstance.setOption(chartOption);
}

function generatePieOption(chartOption, extraOps) {
    var unit = _.get(chartOption.extra, "unit", "");
    var option = {
        color: _.get(extraOps, "chartColorArr", echartsColorArr),
        title: {
            show: _.get(extraOps, "showTitle", false),
            text: chartOption.title.text,
            left: 'center',
            textStyle: {
                color: _.get(extraOps, "titleFontColor", '#C4CCD3'),
                fontSize: _.get(extraOps, "titleFontSize", 20)
            }
        },
        tooltip: _.get(extraOps, "tooltip", {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c}" + unit + " ({d}%)"
        }),
        legend: _.merge({
            itemHeight: 8,
            itemGap: 10,
            selectedMode: chartOption.legend.selectedMode,
            data: chartOption.legend.data
        }, extraOps.legend),
        grid: extraOps.grid,
        series: chartOption.series
    };
    if (_.get(extraOps, "enableVisualMap", false)) {
        var min = deduceNumericScale(chartOption.extra, "visualMapMin", 0);
        var max = deduceNumericScale(chartOption.extra, "visualMapMax", 100);
        option.visualMap = {
            type: 'continuous',
            calculable: _.get(extraOps, "visualMapCalculable", false),
            orient: _.get(extraOps, "visualMapOrient", "horizontal"),
            bottom: _.get(extraOps, "visualMapBottom", 25),
            left: _.get(extraOps, "visualMapLeft", 10),
            min: min,
            max: max,
            text: [max + unit, min + unit],
            dimension: 0,
            seriesIndex: _.get(extraOps, "visualMapSeriesIndex", 1),
            textStyle: {
                color: '#fff'
            },
            color: _.get(extraOps, "chartColorArr", echartsColorArr)
        };
    }
    _.forEach(chartOption.series, function (seriesConfig) {
        _.merge(seriesConfig, extraOps.seriesStyle);
    });
    return option;
}

function renderBarChart(params) {
    disposeEchartInstance(params.containerId);
    var echartInstance = initEchartInstanceAndShow(params.containerId, echartsDefaultOption);
    loadDataAsync({
        queryData: params.queryData,
        targetUrl: params.targetUrl,
        callbackFn: function (resp) {
            var respData = eval(resp);
            doRenderChart(echartInstance, respData.barOption, params.extraOps);
        }
    });
    return echartInstance;
}

function renderBarAndLineChart(params) {
    disposeEchartInstance(params.containerId);
    var echartInstance = initEchartInstanceAndShow(params.containerId, echartsDefaultOption);
    var barExtraOps = params.extraOps.bar;
    var lineExtraOps = params.extraOps.line;
    loadDataAsync({
        queryData: params.queryData,
        targetUrl: params.targetUrl,
        callbackFn: function (resp) {
            var respData = eval(resp);
            var barOption = respData.barOption;
            var lineOption = respData.lineOption;
            if (_.isNull(barOption) || _.isUndefined(barOption)) {
                showTipDialog('tipDialog', '后台无数据，请联系相关接口人！');
                return;
            }
            var chartOption = generateChartOption(barOption, barExtraOps);
            var lineChartOption = generateChartOption(lineOption, lineExtraOps);
            chartOption.yAxis.push(lineChartOption.yAxis[0]);
            _.forEach(lineChartOption.series, function (seriesItem) {
                seriesItem.yAxisIndex = 1;
                chartOption.series.push(seriesItem);
            });
            _.forEach(lineOption.legend.data, function (legendDataItem) {
                chartOption.legend.data.push(legendDataItem);
            });
            var selected = {};
            _.forEach(chartOption.series, function (seriesItem) {
                selected[seriesItem.name] = false;
            });
            if (!_.isNull(barExtraOps.legend.selected)) {
                chartOption.legend.selected = _.merge(selected, barExtraOps.legend.selected);
            }
            setChartOption(echartInstance, chartOption);
        }
    });
    return echartInstance;
}

function renderLineChart(params) {
    disposeEchartInstance(params.containerId);
    var echartInstance = initEchartInstanceAndShow(params.containerId, echartsDefaultOption);
    loadDataAsync({
        queryData: params.queryData,
        targetUrl: params.targetUrl,
        callbackFn: function (resp) {
            var respData = eval(resp);
            doRenderChart(echartInstance, respData.lineOption, params.extraOps);
        }
    });
    return echartInstance;
}

function renderPieChart(params) {
    disposeEchartInstance(params.containerId);
    var echartInstance = initEchartInstanceAndShow(params.containerId, null);
    // var tooltipConfig = {
    //     trigger: 'item',
    //     formatter: "{b} : {c} ({d}%)"
    // };
    // var extraOps = params.extraOps;
    // var tooltip = _.get(extraOps, "tooltip", tooltipConfig);
    // params.extraOps.tooltip = _.merge(tooltip, tooltipConfig);
    loadDataAsync({
        queryData: params.queryData,
        targetUrl: params.targetUrl,
        callbackFn: function (resp) {
            var respData = eval(resp);
            var option = generatePieOption(respData.pieOption, params.extraOps);
            setChartOption(echartInstance, option);
        }
    });
    return echartInstance;
}

function renderRankBarAndPieChart(params) {

    loadDataAsync({
        queryData: params.queryData,
        targetUrl: params.targetUrl,
        callbackFn: function (resp) {
            var respData = eval(resp);
            var pieOption = respData.pieOption;
            var barOption = respData.barOption;
            // doRenderRankBarChart(barOption, params.extraOps.bar);
            // doRenderPieChart(pieOption, params.extraOps.pie);
            doRenderRankBarAndPieChart(params.containerId, barOption, pieOption, params.extraOps.bar, params.extraOps.pie)
        }
    });
}

function doRenderRankBarAndPieChart(containerId, barOption, pieOption, barExtraOps, pieExtraOps) {
    if (_.isNull(barOption)) {
        showTipDialog('tipDialog', '后台无数据，请联系相关接口人！');
        return;
    }
    var echartInstance = initEchartInstanceAndShow(containerId, null);
    var chartOption = generateRankStyleBarOption(barOption, barExtraOps);

    var pieUnit = pieOption.extra.unit;
    var pieSeriesLabel = {
        normal: {
            formatter: "{b}: {c}" + pieUnit + "({d}%)"
        }
    };
    chartOption.tooltip = {};
    _.forEach(pieOption.series, function (item) {
        item.radius = _.get(pieExtraOps, "seriesStyle.radius", ['10%', '40%']);
        item.center = _.get(pieExtraOps, "seriesStyle.center", ['75%', '50%']);
        item.label = _.get(pieExtraOps, "seriesStyle.label", pieSeriesLabel);
        chartOption.series.push(item);
    });

    if (_.get(pieExtraOps, "enableVisualMap", false)) {
        var min = deduceNumericScale(chartOption.extra, "visualMapMin", 0);
        var max = deduceNumericScale(chartOption.extra, "visualMapMax", 100);
        chartOption.visualMap = [chartOption.visualMap, {
            type: 'continuous',
            calculable: _.get(pieExtraOps, "visualMapCalculable", false),
            orient: _.get(pieExtraOps, "visualMapOrient", "horizontal"),
            bottom: _.get(pieExtraOps, "visualMapBottom", 25),
            left: _.get(pieExtraOps, "visualMapLeft", 10),
            min: min,
            max: max,
            text: [max + pieUnit, min + pieUnit],
            dimension: 0,
            seriesIndex: _.get(pieExtraOps, "visualMapSeriesIndex", 1),
            textStyle: {
                color: '#fff'
            },
            color: _.get(pieExtraOps, "visualMapColorArr", gradientColorArr)
        }];
    }
    setChartOption(echartInstance, chartOption);
}

function doRenderRankBarChart(barOption, extraOps) {
    if (_.isNull(barOption)) {
        showTipDialog('tipDialog', '后台无数据，请联系相关接口人！');
        return;
    }
    var echartInstance = initEchartInstanceAndShow(extraOps.containerId, null);
    extraOps.chartColorArr = gradientColorArr;
    var chartOption = generateRankStyleBarOption(barOption, extraOps);
    setChartOption(echartInstance, chartOption);
}

function doRenderPieChart(pieOption, extraOps) {
    if (_.isNull(pieOption)) {
        showTipDialog('tipDialog', '后台无数据，请联系相关接口人！');
        return;
    }
    var echartInstance = initEchartInstanceAndShow(extraOps.containerId, null);
    extraOps.chartColorArr = gradientColorArr;

    var chartOption = generatePieOption(pieOption, extraOps);
    setChartOption(echartInstance, chartOption);
}

function renderRankBarAndMapChart(params) {
    var mapChartInstance = initEchartInstanceAndShow(params.containerId, null);
    loadDataAsync({
        queryData: params.queryData,
        targetUrl: params.targetUrl,
        callbackFn: function (resp) {
            var respData = eval(resp);
            var mapOption = respData.mapOption;
            var barOption = respData.barOption;
            doRenderRankBarAndMapChart(mapChartInstance, mapOption, barOption, params.mapType);
        }
    });
}

function doRenderChart(echartInstance, chartOption, extraOps) {
    if (_.isNull(chartOption) || _.isUndefined(chartOption)) {
        showTipDialog('tipDialog', '后台无数据，请联系相关接口人！');
        return;
    }
    setChartOption(echartInstance, generateChartOption(chartOption, extraOps));
}

function generateChartOption(chartOption, extraOps) {
    var option = {
        color: _.get(extraOps, "chartColorArr", echartsColorArr),
        tooltip: _.get(extraOps, "tooltip", {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        }),
        title: {
            show: _.get(extraOps, "showTitle", false),
            text: chartOption.title.text,
            textStyle: {
                color: '#C4CCD3',
                fontSize: 20
            }
        },
        legend: _.merge({
            itemHeight: 8,
            itemGap: 10,
            selectedMode: chartOption.legend.selectedMode,
            data: chartOption.legend.data
        }, extraOps.legend),
        grid: _.merge({
            left: 'center'
        }, extraOps.grid),
        xAxis: [
            {
                axisLabel: {
                    show: _.get(extraOps, "xAxisLabelShow", true),
                    interval: _.get(extraOps, "xAxisLabelInterval", 0),
                    rotate: _.get(extraOps, "xAxisLabelRotate", 0)
                },
                boundaryGap: _.get(extraOps, "boundaryGap", false),
                data: chartOption.extra.xAxisData
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '单位：' + _.get(extraOps, "yAxisName", chartOption.extra.yAxisName),
                axisLine: {
                    lineStyle: {
                        color: '#FFF'
                    }
                },
                splitLine: {
                    show: _.get(extraOps, "showSplitLine", false),
                    lineStyle: {
                        opacity: 0.5,
                        type: 'dashed'
                    }
                },
                position: _.get(extraOps, "yAxisPosition", 'left'),
                axisLabel: {
                    formatter: '{value}'
                }
            }
        ],
        series: chartOption.series
    };

    var dataZoomConfig = [
        {
            type: 'slider',
            textStyle: {
                color: '#FFF'
            },
            start: 60,
            end: 100,
            backgroundColor: 'rgba(47,69,84,0.5)',
            dataBackground: {
                areaStyle: {
                    color: 'rgba(137, 255, 12, 0.8)'
                },
                lineStyle: {

                }
            }
        }
    ];
    if (extraOps.showDataZoom === true) {
        option.dataZoom = _.merge(dataZoomConfig, extraOps.dataZoom);
    }

    // 每个系列的配置项一样
    if (!_.isNull(extraOps.seriesStyle) && !_.isUndefined(extraOps.seriesStyle)) {
        _.forEach(chartOption.series, function (seriesConfig) {
            _.merge(seriesConfig, extraOps.seriesStyle);
        });
    }

    // 支持每个系列的配置项各不相同
    if (!_.isNull(extraOps.seriesStyleArr) && !_.isUndefined(extraOps.seriesStyleArr)) {
        var arrLen1 = _.size(extraOps.seriesStyleArr);
        _.forEach(chartOption.series, function (seriesConfig, idx) {
            _.merge(seriesConfig, extraOps.seriesStyleArr[idx % arrLen1]);
        });
    }

    if (!_.isNull(extraOps.seriesItemStyleArr) && !_.isUndefined(extraOps.seriesItemStyleArr)) {
        var arrLen2 = _.size(extraOps.seriesItemStyleArr);
        _.forEach(chartOption.series, function (seriesConfig, idx) {
            seriesConfig.itemStyle = extraOps.seriesItemStyleArr[idx % arrLen2];
        });
    }
    return option;
}

// 福建地市散点图
var demoData = [
    {name: '福州', value: 83.12},
    {name: '厦门', value: 76},
    {name: '宁德', value: 32},
    {name: '莆田', value: 38},
    {name: '泉州', value: 71},
    {name: '漳州', value: 59},
    {name: '龙岩', value: 24.23},
    {name: '三明', value: 14},
    {name: '南平', value: 10}
];

var fujianCityCoordMap = {
    "福州": [119.306239, 26.075302],
    "厦门": [118.11022, 24.490474],
    "莆田": [119.007558, 25.431011],
    "三明": [117.235001, 26.465444],
    "泉州": [118.589421, 24.908853],
    "漳州": [117.361801, 24.110897],
    "南平": [117.658459, 27.355627],
    "龙岩": [116.32978, 25.291603],
    "宁德": [119.527082, 26.95924],
    "仓山区": [119.273545,26.046743],
    "鼓楼区": [119.303917,26.081983],
    "台江区": [119.314041,26.052843],
    "马尾区": [119.455588,25.9895],
    "晋安区": [119.328521,26.082107],
    "闽侯县": [119.131724,26.150047],
    "连江县": [119.539704,26.197364],
    "罗源县": [119.549776,26.489558],
    "闽清县": [118.863361,26.221197],
    "平潭县": [119.790168,25.49872],
    "永泰县": [118.932592,25.866694],
    "福清市": [119.384201,25.72071],
    "长乐市": [119.523266,25.962888],
    "思明区": [118.082649,24.445484],
    "海沧区": [118.032984,24.484685],
    "湖里区": [118.146768,24.512904],
    "集美区": [118.097337,24.575969],
    "同安区": [118.152041,24.723234],
    "翔安区": [118.248034,24.618543],
    "蕉城区": [119.526299,26.66061],
    "霞浦县": [120.005146,26.885703],
    "屏南县": [118.985895,26.908276],
    "古田县": [118.746284,26.577837],
    "寿宁县": [119.514986,27.454479],
    "柘荣县": [119.900609,27.233933],
    "周宁县": [119.339025,27.104591],
    "福安市": [119.64785,27.08834],
    "福鼎市": [120.216977,27.324479],
    "城厢区": [118.993884,25.419319],
    "涵江区": [119.116289,25.45872],
    "荔城区": [119.015061,25.431941],
    "秀屿区": [119.105494,25.31836],
    "仙游县": [118.691637,25.362093],
    "鲤城区": [118.587097,24.907424],
    "丰泽区": [118.613172,24.891173],
    "泉港区": [118.916309,25.119815],
    "洛江区": [118.671193,24.939796],
    "惠安县": [118.796607,25.030801],
    "金门县": [118.323221,24.436417],
    "安溪县": [118.186288,25.055954],
    "永春县": [118.294048,25.321565],
    "德化县": [118.241094,25.491493],
    "石狮市": [118.648066,24.732204],
    "晋江市": [118.551682,24.781636],
    "南安市": [118.386279,24.960385],
    "芗城区": [117.653968,24.510787],
    "龙文区": [117.709754,24.503113],
    "云霄县": [117.339573,23.957936],
    "漳浦县": [117.613808,24.117102],
    "诏安县": [117.175184,23.711579],
    "东山县": [117.430061,23.701262],
    "长泰县": [117.759153,24.625449],
    "南靖县": [117.35732,24.514654],
    "平和县": [117.315017,24.363508],
    "华安县": [117.534103,25.004425],
    "龙海市": [117.818197,24.446706],
    "新罗区": [117.037155,25.098312],
    "长汀县": [116.357581,25.833531],
    "永定区": [116.732091,24.723961],
    "上杭县": [116.420098,25.049518],
    "武平县": [116.100414,25.095386],
    "连城县": [116.754472,25.710538],
    "漳平市": [117.419998,25.290184],
    "梅列区": [117.645855,26.271711],
    "三元区": [117.608044,26.234019],
    "清流县": [116.816909,26.177796],
    "明溪县": [117.202226,26.355856],
    "宁化县": [116.654365,26.261754],
    "大田县": [117.847115,25.692699],
    "尤溪县": [118.190467,26.170171],
    "沙县": [117.792396,26.397199],
    "将乐县": [117.471372,26.728952],
    "泰宁县": [117.17574,26.900259],
    "建宁县": [116.848443,26.833588],
    "永安市": [117.365052,25.941937],
    "延平区": [118.182036,26.637438],
    "顺昌县": [117.810357,26.793288],
    "浦城县": [118.541256,27.917263],
    "建阳区": [118.120464,27.331876],
    "松溪县": [118.785468,27.526232],
    "光泽县": [117.334106,27.540987],
    "政和县": [118.857642,27.366104],
    "邵武市": [117.492533,27.340326],
    "武夷山市": [118.035309,27.756647],
    "建瓯市": [118.304966,27.022774]
};

function generateGeoOption(mapType) {
    return {
        map: mapType,
        label: {
            normal: {
                show: false,
                textStyle: {
                    color: '#fff'
                }
            },
            emphasis: {
                show: false
            }
        },
        roam: true,
        itemStyle: {
            normal: {
                borderColor: 'rgba(147, 235, 248, 1)',
                borderWidth: 1,
                areaColor: {
                    type: 'radial',
                    x: 0.5,
                    y: 0.5,
                    r: 0.8,
                    colorStops: [{
                        offset: 0,
                        color: 'rgba(175,238,238, 0)' // 0% 处的颜色
                    }, {
                        offset: 1,
                        color: 'rgba(47,79,79, .1)' // 100% 处的颜色
                    }],
                    globalCoord: false // 缺省为 false
                },
                shadowColor: 'rgba(128, 217, 248, 1)',
                shadowOffsetX: -2,
                shadowOffsetY: 2,
                shadowBlur: 10
            },
            emphasis: {
                areaColor: '#389BB7',
                borderWidth: 0
            }
        }
    };
}

function loadMapScatterData(rawData, coordMap, unit) {
    var res = [];
    for (var i = 0; i < rawData.length; i++) {
        var geoCoord = coordMap[rawData[i].name];
        if (geoCoord) {
            res.push({
                name: rawData[i].name,
                value: _.concat(geoCoord, [rawData[i].value]),
                tooltip: {
                    trigger: 'item',
                    formatter: function (item) {
                        return item.name + ": " + item.value[2] + unit;
                    }
                }
            });
        }
    }
    return res;
}

function generateFujianMapOption(mapOption, coordMap, mapType) {
    var rawData = _.get(mapOption, "data", demoData);
    var titleText = _.get(mapOption, "titleText", "");
    var unit = _.get(mapOption, "unit", "");
    if (coordMap == null) {
        coordMap = fujianCityCoordMap;
    }
    if (mapType == null) {
        mapType = "福建";
    }
    var min = deduceNumericScale(mapOption, "visualMapMin", 0);
    var max = deduceNumericScale(mapOption, "visualMapMax", 100);
    var fujianMapOption = {
        color: _.get(mapOption, "chartColorArr", echartsColorArr),
        title: {
            show: _.get(mapOption, "showTitle", true),
            text: titleText,
            left: 'center',
            textStyle: {
                color: '#C4CCD3',
                fontSize: _.get(mapOption, "fontSize", 18)
            }
        },
        tooltip: {
            show: _.get(mapOption, "showToolTip", false),
            trigger: 'item'
        },
        visualMap: {
            type: 'continuous',
            orient: _.get(mapOption, "visualMapOrient", "vertical"),
            calculable : _.get(mapOption, "visualMapCalculable", false),
            bottom: _.get(mapOption, "visualMapBottom", 25),
            left: _.get(mapOption, "visualMapLeft", 5),
            min: min,
            max: max,
            text: [max + unit, min + unit],
            seriesIndex: 0,
            textStyle:{
                color:'#fff'
            },
            color: _.get(mapOption, "visualMapColorArr", gradientColorArr)
        },
        geo: generateGeoOption(mapType),
        series : [
            {
                type: 'effectScatter',
                coordinateSystem: 'geo',
                data: loadMapScatterData(rawData, coordMap, unit),
                symbolSize: _.get(mapOption, "symbolSize", 13),
                symbolRotate:  _.get(mapOption, "symbolRotate", 35),
                showEffectOn: 'render',
                rippleEffect: {
                    brushType: 'stroke'
                },
                hoverAnimation: true,
                label: {
                    normal: {
                        backgroundColor: 'rgba(68, 70, 86, 0.8)',
                        borderRadius: 3,
                        borderWidth: 1,
                        borderColor: '#333',
                        color: 'auto',
                        lineHeight: 25,
                        textStyle: {
                            fontSize: 15
                        },
                        rich: {
                            title: {
                                color: '#C0FF3E',
                                align: 'center',
                                fontSize: 16
                            },
                            cell: {
                                padding: [0, 10, 0, 0],
                                align: 'center',
                                fontSize: 16
                            }
                        },
                        formatter: function (item) {
                            return '{title|' + item.name + '：} {cell|' + item.value[2] + unit + '}';
                        },
                        position: [_.get(mapOption, "labelPositionRight", 30), _.get(mapOption, "labelPositionTop", -5)],
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#f4e925',
                        shadowBlur: 10,
                        shadowColor: '#333'
                    }
                },
                zlevel: 1
            }
        ]
    };
    return fujianMapOption;
}

function renderFujianProvinceMap(echartInstance, mapOption) {
    if (_.isNull(mapOption) || _.isUndefined(mapOption)) {
        showTipDialog('tipDialog', '后台无数据，请联系相关接口人！');
        return;
    }
    var fujianMapOption = generateFujianMapOption(mapOption, fujianCityCoordMap, "福建");
    setChartOption(echartInstance, fujianMapOption);
}

function doRenderRankBarAndMapChart(echartInstance, mapOption, barOption, mapType) {
    if (_.isNull(mapOption) || _.isUndefined(mapOption)) {
        showTipDialog('tipDialog', '后台无数据，请联系相关接口人！');
        return;
    }
    var fujianMapOption = generateFujianMapOption(mapOption, fujianCityCoordMap, mapType);

    if (!_.isNull(barOption) && !_.isUndefined(barOption)) {
        var rankBarOption = generateRankStyleBarOption(barOption, {});
        fujianMapOption.title = [{show: false}, rankBarOption.title];
        _.forEach(rankBarOption.series, function (item) {
            fujianMapOption.series.push(item);
        });
        fujianMapOption.visualMap.left = -1000;
        fujianMapOption.legend = rankBarOption.legend;
        fujianMapOption.tooltip = [rankBarOption.tooltip];
        fujianMapOption.visualMap = [fujianMapOption.visualMap, rankBarOption.visualMap];
        fujianMapOption.grid = rankBarOption.grid;
        fujianMapOption.xAxis = rankBarOption.xAxis;
        fujianMapOption.yAxis = rankBarOption.yAxis;
        fujianMapOption.geo.left = _.get(mapOption, "geoLeft", '40%');
    }

    setChartOption(echartInstance, fujianMapOption);
}

function generateRankStyleBarOption(barOption, extraOps) {
    if (extraOps == null) {
        extraOps = {};
    }
    var extraOption = _.merge(barOption.extra, extraOps);
    var min = deduceNumericScale(extraOption, "visualMapMin", 0);
    var max = deduceNumericScale(extraOption, "visualMapMax", 100);
    var unit = _.get(extraOption, "unit", "");
    var labelConfig = {
        normal: {
            show: true,
            position: ['100%', '30%'],
            textStyle: {
                color: '#EEE',
                fontSize: 15
            }
        }
    };
    _.forEach(barOption.series, function (series) {
        series.label = labelConfig;
    });
    var option = {
        color: _.get(extraOption, "chartColorArr", echartsColorArr),
        title: {
            show: true,
            left: _.get(extraOption, 'titleLeft', '20%'),
            text: barOption.title.text,
            // subtext: "(" + extraOption.subTitle + ")",
            textAlign: 'center',
            textStyle: {
                color: '#0fd307',
                fontSize: 15
            },
            subtextStyle: {
                color: '#EEE',
                fontSize: 12
            }
        },
        legend: {
            show: false
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        visualMap: {
            type: 'continuous',
            calculable : _.get(extraOption, "visualMapCalculable", false),
            orient: _.get(extraOption, "visualMapOrient", "horizontal"),
            bottom: _.get(extraOption, "visualMapBottom", 25),
            left: _.get(extraOption, "visualMapLeft", 10),
            min: min,
            max: max,
            text: [max + unit, min + unit],
            dimension: 0,
            seriesIndex: _.get(extraOption, "visualMapSeriesIndex", 1),
            textStyle:{
                color:'#fff'
            },
            color: _.get(extraOption, "visualMapColorArr", gradientColorArr)
        },
        grid: {
            top: _.get(extraOption, "gridTop", 50),
            left: _.get(extraOption, "gridLeft", 60),
            width: _.get(extraOption, "gridWidth", '20%'),
            height: _.get(extraOption, "gridHeight", '60%')
        },
        yAxis: [
            {
                type: 'category',
                inverse: true,
                name: '',
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    color: '#fff'
                },
                splitLine: {
                    show: false
                },
                data: extraOption.xAxisData
            }
        ],
        xAxis: [
            {
                type: 'value',
                show: false,
                axisLine: {
                    lineStyle: {
                        color: '#FFF'
                    }
                },
                splitLine: {
                    show: false
                },
                name: _.get(extraOption, "yAxisName", ''),
                position: 'top',
                axisLabel: {
                    formatter: '{value}'
                }
            }
        ],
        series: barOption.series
    };
    return option;
}

function deduceNumericScale(options, prop, defVal) {
    var val = _.get(options, prop, defVal);
    if (!_.isNumber(val)) {
        return val;
    }
    val = val < 0 ? 0 : val;
    if (val > 0 && val < 1) {
        val = _.round(val, 4);
    } else {
        val = _.round(val, 0);
    }
    return val;
}