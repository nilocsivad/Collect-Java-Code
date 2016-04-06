/**
 * 
 */




var a = new Array( 10 );
var menu = '';
for ( var i = 0; i < a.length; i++ ) {
	menu += '<li class="style_' + a[ i ] + '" >' + a[ i ] + '</li>';
}


/*
 * 
 * 
 * 
 * 
 */

String.prototype.format = function() {
	var args = arguments;
	return this.replace( /{(\d{1})}/g, function() {
		return args[ arguments[ 1 ] ];
	} );
};

var a = new Array( 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 );
var m = '<li class="style_{0}" >{0}</li>';

for ( var i = 0; i < a.length; i++ ) {
	menu += m.format( a[ i ] );
}