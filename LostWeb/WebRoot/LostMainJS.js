var callBackURL = document.getElementById("callBackURLHiddenButtonId").getValue();
function setBlackMarketRadioButtonValue() {
	var commodityInfo = null;
	var commodityTable = document.getElementById("table_CommodityListId");
	var commodityRadios = commodityTable.getElementsByTagName("input");
	for (var i = 0; i < commodityRadios.length; i++) {
		if (commodityRadios[i].getChecked()) {
			commodityInfo = commodityRadios[i].getValue();
		}
	}
	var blackMarketHiddenButton = document.getElementById("blackMarketHiddenButtonId");
	blackMarketHiddenButton.setValue(commodityInfo);
}
function setCartListValue() {
	var commodityInfo = document.getElementById("cartListId").getValue();
	var cartListHiddenButton = document.getElementById("cartListHiddenButtonId");
	cartListHiddenButton.setValue(commodityInfo);
}
function buy() {
	var commodityInfo = null;
	var commodityTable = document.getElementById("table_CommodityListId");
	var commodityRadios = commodityTable.getElementsByTagName("input");
	for (var i = 0; i < commodityRadios.length; i++) {
		if (commodityRadios[i].getChecked()) {
			commodityInfo = commodityRadios[i].getValue();
		}
	}
	if (commodityInfo == null) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please pick up the item you would like to buy in the Market.");
		return;
	}
	var cL = document.getElementById("currentLocationHiddenButtonId").getValue();
	var userCapacity = document.getElementById("userCapacityHiddenButtonId").getValue();
	var cURL = document.getElementById("callBackURLHiddenButtonId").getValue();
	var quantity = document.getElementById("quantityTextId").getValue();
	if (quantity == 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity other than 0");
		return;
	}
	if (quantity < 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity other than negative");
		return;
	}
	if (Math.floor(quantity) != quantity) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity.");
		return;
	}
	var t = commodityInfo.indexOf("_");
	var price = commodityInfo.substring(t + 1, commodityInfo.length);
	var userCash = document.getElementById("userCash").getValue();
	if (price * quantity > userCash) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "You don't have enough money man. Please input appropriate quantity.");
		return;
	}
	if (parseInt(quantity) > parseInt(userCapacity)) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Warehouse capacity exceeded. Maximum you can buy is " + userCapacity);
		return;
	}
	var userAction = document.getElementById("userActionId");
	userAction.setValue("buy");
	var cQuantityBuy = document.getElementById("cQuantityBuyId");
	cQuantityBuy.setValue(quantity);
	var cQuantityBuy = document.getElementById("isRefreshId");
	cQuantityBuy.setValue(Math.random());
	//document.setLocation("http://www.google.com/?pp="+uC);
	document.getElementById("marketFormId").submit();
}
function sell() {
	var sellInfo = document.getElementById("cartListId").getValue();
	if (sellInfo == null) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please pick up the item you would like to buy in the Cart.");
		return;
	}
	var quantity = document.getElementById("quantityTextId").getValue();
	if (quantity == 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity other than 0");
		return;
	}
	if (quantity < 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity other than negative");
		return;
	}
	if (Math.floor(quantity) != quantity) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity.");
		return;
	}
	var sellPrice = 0;
	var t = sellInfo.indexOf("_");
	var sellId = sellInfo.substring(0, t);
	var sellPriceQuantity = sellInfo.substring(t + 1, sellInfo.length);
	var tt = sellPriceQuantity.indexOf("_");
	var sellOriginalPrice = sellPriceQuantity.substring(0, tt);
	var sellQuantity = sellPriceQuantity.substring(tt + 1, sellPriceQuantity.length);
	if (quantity > parseInt(sellQuantity)) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity less than you have.");
		return;
	}
	var commodityTable = document.getElementById("table_CommodityListId");
	var commodityRadios = commodityTable.getElementsByTagName("input");
	var flag = 0;
	for (var i = 0; i < commodityRadios.length; i++) {
		var buyInfo = commodityRadios[i].getValue();
		var ttt = buyInfo.indexOf("_");
		var itemId = buyInfo.substring(0, ttt);
		if (itemId == sellId) {
			sellPrice = buyInfo.substring(ttt + 1, buyInfo.length);
			sellInfo = sellId + "_" + sellPrice + "_" + sellQuantity + "_" + sellOriginalPrice;
			flag = 1;
		}
	}
	if (flag == 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Sorry, item can be sold only when it is available in market.");
		return;
	}
	var cartListHiddenButton = document.getElementById("cartListHiddenButtonId");
	cartListHiddenButton.setValue(sellInfo);
	var userAction = document.getElementById("userActionId");
	userAction.setValue("sell");
	var cQuantityBuy = document.getElementById("cQuantitySellId");
	cQuantityBuy.setValue(quantity);
	var cQuantityBuy = document.getElementById("isRefreshId");
	cQuantityBuy.setValue(Math.random());
	//document.setLocation("http://www.google.com/?pp="+uC);
	document.getElementById("marketFormId").submit();
}
function generateMarket(k) {
	var userAction = document.getElementById("userActionMoveId");
	userAction.setValue("move");
	var moveId = document.getElementById("locationMoveId");
	moveId.setValue(k);
	var cQuantityBuy = document.getElementById("isRefreshMoveId");
	cQuantityBuy.setValue(Math.random());
	document.getElementById("moveFormId").submit();
}
function invite(k) {
	var userAction = document.getElementById("userActionInviteId");
	userAction.setValue("invite");
	document.getElementById("inviteFormId").submit();
}
function restartGame(k) {
	var userAction = document.getElementById("userActionRestartId");
	userAction.setValue("restart");
	document.getElementById("restartFormId").submit();
}

