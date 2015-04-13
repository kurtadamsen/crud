
var polardata = [
    {
        value: 300,
        color:"#F7464A",
        highlight: "#FF5A5E",
        label: "Red"
    },
    {
        value: 50,
        color: "#46BFBD",
        highlight: "#5AD3D1",
        label: "Green"
    },
    {
        value: 100,
        color: "#FDB45C",
        highlight: "#FFC870",
        label: "Yellow"
    },
    {
        value: 40,
        color: "#949FB1",
        highlight: "#A8B3C5",
        label: "Grey"
    },
    {
        value: 120,
        color: "#4D5360",
        highlight: "#616774",
        label: "Dark Grey"
    }

];

var piedata = [
    {
        value: 0,
        color:"Green",
        highlight: "#11aa11",
        label: "OK"
    },
    {
        value: 0,
        color: "#eedd11",
        highlight: "#ffee22",
        label: "Warning"
    },
    {
        value: 0,
        color: "#e00000",
        highlight: "#f01111",
        label: "Errors"
    }
];

piedata[0].value = 100;
piedata[1].value = 200;
var options = [];

var ctx1 = document.getElementById("myChart1").getContext("2d");
var ctx2 = document.getElementById("myChart2").getContext("2d");
var ctx3 = document.getElementById("myChart3").getContext("2d");

var myPieChart = new Chart(ctx1).Pie(piedata,options);
var myNewChart = new Chart(ctx2).PolarArea(polardata);
var myDoughnutChart = new Chart(ctx3).Doughnut(piedata,options);
