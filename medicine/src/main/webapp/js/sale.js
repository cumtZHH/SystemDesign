/*window.onload = function () {
    if (!document.getElementsByClassName) {
        document.getElementsByClassName = function (cls) {
            var ret = [];
            var els = document.getElementsByTagName('*');
            for (var i = 0, len = els.length; i < len; i++) {
                if (els[i].className === cls
                    || els[i].className.indexOf(cls + ' ') >= 0
                    || els[i].className.indexOf(' ' + cls + ' ') >= 0
                    || els[i].className.indexOf(' ' + cls) >= 0) {
                    ret.push(els[i]);
                }
            }
            return ret;
        }
    }*/
	var saleTable = document.getElementById('saleTable');
    var tr = saleTable.children[1].rows;
    var priceTotal = document.getElementById('priceTotal');
    function getSubTotal(tr) {
        var tds = tr.cells;
        var price = parseFloat(tds[3].innerHTML);
        var count = parseInt(tds[4].innerHTML);
        var SubTotal = parseFloat(price * count);
        tds[5].innerHTML = SubTotal.toFixed(1);
    }
    function getTotal(){
    	var total=0;
    	for (var i = 0, len = tr.length; i < len; i++) {
        	total=total+tr[i].cells[5].innerHTML;
        }
    	priceTotal.innerHTML=total;
    }
    