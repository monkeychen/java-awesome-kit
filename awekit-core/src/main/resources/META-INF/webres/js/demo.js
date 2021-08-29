function refreshPage() {
    var cityId = $('#city_id').val();
    var beginTime = $('#begin_time').val();
    var endTime = $('#end_time').val();
    var indicatorName = $('#indicator_name').val();

    renderLineChart({
        containerId: "echarts_area",
        queryData: {
            cityId: cityId,
            beginTime: beginTime,
            endTime: endTime,
            indicatorName: indicatorName,
            countyName: $('#view_data_cache').attr("countyName")
        },
        targetUrl: "loadTrendLineData",
        extraOps: {
            chartColorArr: echartsColorArr,
            showTitle: true,
            legend: {bottom: 5},
            grid: {bottom: 100},
            xAxisLabelInterval: 0,
            // seriesStyle: lineSeriesStyle,
            showDataZoom: false,
            dataZoom: [{start: 60, end: 100, bottom: 40}]
        }
    });
}