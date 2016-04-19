String.prototype.format = function() {
	var args = arguments;
	return this.replace(/{(\d{1})}/g, function() {
		return new String(args[arguments[1]]);
	});
};