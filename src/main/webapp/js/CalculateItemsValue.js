new Function CalculateItemsValue() {
	var total = 0;
	list = document.getElementsByClassName("price");
	for (price : list){
		total += price;
	}
	document.getElementById("TotalPrice").innerHTML = "$" + total;



}
