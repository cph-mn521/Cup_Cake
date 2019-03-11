function CalculateItemsValue() {
	var total = 0;
	list = document.getElementsByClassName("price");
	for (price in list){
		total += price;
	}
	document.getElementById("TotalPrice").innerHTML = "$" + total;
}