/**
 * Funções úteis para máscaras e formatações em javascript
 * @param v
 * @returns
 */


function mascararTel(v){
    v=v.replace(/\D/g,"");            
    v=v.replace(/^(\d{2})(\d)/g,"($1) $2"); 
    v=v.replace(/(\d)(\d{4})$/,"$1-$2");   
    return v;
}

	function formataDinheiro(n) {
		
		return "R$ " + n.toFixed(2).replace('.', ',').replace(/(\d)(?=(\d{3})+\,)/g, "$1.");
	}

function formatBR(value, decimais) {

    decimais = decimais || 2;
    var mi = value.length - parseInt(decimais);
    var sm = parseInt(mi / 3);
    var regx = "", repl = "";

    for (var i = 0; i < sm; i++) {
        regx = regx.concat('([0-9]{3})');
        repl = repl.concat('.$' + (i + 1));
    }

    regx = regx.concat('([0-9]{' + decimais + '})') + "$";
    repl = repl.concat(',$' + (sm + 1));
    value = value.toString().replace(new RegExp(regx, 'g'), repl);
    return (mi % 3) === 0 ? value.substr(1) : value;
}