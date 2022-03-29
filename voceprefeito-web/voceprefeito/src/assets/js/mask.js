var v_obj;
var v_fun;

function mascara(o, f) {
	v_obj = o;
	v_fun = f;
	setTimeout("execMascara()", 1);
}

function execMascara() {
	v_obj.value = v_fun(v_obj);
}

function moeda(obj) {
	v = obj.value;
	v = v.replace(/\D/g, "");
	v = v.replace(/(\d{1})(\d{15})$/, "$1.$2");
	v = v.replace(/(\d{1})(\d{11})$/, "$1.$2");
	v = v.replace(/(\d{1})(\d{8})$/, "$1.$2");
	v = v.replace(/(\d{1})(\d{5})$/, "$1.$2");
	v = v.replace(/(\d{1})(\d{1,2})$/, "$1,$2");
	return v;
}

function cpfCnpj(obj){
	v = obj;
	v = v.replace(/\D/g,"");
    if(v.length < 14){
    	
        v=v.replace(/(\d{3})(\d)/,"$1.$2")
        v=v.replace(/(\d{3})(\d)/,"$1.$2")
        v=v.replace(/(\d{3})(\d{1,2})$/,"$1-$2")
    } else {
        
        v=v.replace(/^(\d{2})(\d)/,"$1.$2")
        v=v.replace(/^(\d{2})\.(\d{3})(\d)/,"$1.$2.$3")
        v=v.replace(/\.(\d{3})(\d)/,".$1/$2")
        v=v.replace(/(\d{4})(\d)/,"$1-$2")
    }
    return v;
}

function cep(obj) {
	v = obj.value;
	v = v.replace(/\D/g, "");
	v = v.replace(/(\d{5})(\d)/, "($1-$2")
	return v;
}

function telefone(obj) {
	v = obj.value;
	v = v.replace(/\D/g, "");
	v = v.replace(/(\d{0})(\d)/, "($1$2")
	v = v.replace(/(\d{2})(\d)/, "$1)$2")
	v = v.replace(/(\d{5})(\d)/, "$1-$2")
	return v;
}

function processo(obj){
	v = obj.value;
	v = v.replace(/\D/g,"");
    v = v.replace(/(\d{4})(\d)/,"$1/$2")
    return v;
}

function formatDouble1(obj) {
	obj.style.textAlign = "right";
	return formatDouble1Sep(obj, '.', ',');
}

function formatDouble2(obj) {
	obj.style.textAlign = "right";
	return formatDouble2Sep(obj, '.', ',');
}

function numberOnly(myfield, e, dec) {
    var key;
    var keychar;

    if (window.event)
            key = window.event.keyCode;
    else if (e)
            key = e.which;
    else
            return true;
   
    keychar = String.fromCharCode(key);

    if ((key==null) || (key==0) || (key==8) ||
            (key==9) || (key==13) || (key==27) )
       return true;

    else if ((("-0123456789").indexOf(keychar) > -1))
       return true;

    else if (dec && (keychar == ",")){
            myfield.form.elements[dec].focus();
            return false;
    }else
            return false;
}


function formatDouble1Sep(nStr, sepMilhar, sepDecimal) {
	tempStr = nStr.value;
	tempStr = somenteDigitosDouble(tempStr);
	menos = "";
	if (tempStr.substring(0, 1) == "-") {
		menos = "-";
		tempStr = ltrim(tempStr, "-");
	}
	tempStr = ltrim(tempStr, "0");
	if (tempStr.length == 1) {
		x1 = '0';
		x2 = sepDecimal + tempStr;
	} else {
		x2 = sepDecimal + tempStr.substring(tempStr.length - 2, tempStr.length);
		x1 = tempStr.substring(0, tempStr.length - 2);
	}
	x1 = addSeparador(x1, sepMilhar);
	if ((x1 == "") && (x2 == ",")) {
		nStr.value = menos + "0,0";
	} else {
		nStr.value = menos + x1 + x2;
	}
}

function formatDouble2Sep(nStr, sepMilhar, sepDecimal) {
	tempStr = nStr.value;
	tempStr = somenteDigitosDouble(tempStr);
	menos = "";
	if (tempStr.substring(0, 1) == "-") {
		menos = "-";
		tempStr = ltrim(tempStr, "-");
	}
	tempStr = ltrim(tempStr, "0");
	if (tempStr.length == 1) {
		x1 = '0';
		x2 = sepDecimal + '0' + tempStr;
	} else if (tempStr.length == 2) {
		x1 = '0';
		x2 = sepDecimal + tempStr;
	} else {
		x2 = sepDecimal + tempStr.substring(tempStr.length - 2, tempStr.length);
		x1 = tempStr.substring(0, tempStr.length - 2);
	}
	x1 = addSeparador(x1, sepMilhar, sepDecimal);
	if ((x1 == "") && (x2 == ",")) {
		if (nStr.value == "-0,00") {
			return "0,00";
		} else {
			return menos + "0,00";
		}
	} else {
		return menos + x1 + x2;
	}
}

function somenteDigitosDouble(sText) {
	var menos = "-";
	var resultado = '';
	for ( var i = 0; i < sText.length; i++) {
		char = sText.charAt(i);
		if (menos.indexOf(char) > -1) {
			resultado = char;
		}
	}
	return resultado + somenteDigitos(sText);
}

function somenteDigitos(sText) {
	var validChars = "0123456789";
	var resultado = '';
	for ( var i = 0; i < sText.length; i++) {
		char = sText.charAt(i);
		if (validChars.indexOf(char) > -1) {
			resultado += char;
		}
	}
	return resultado;
}

function ajustarNumeroDocumento(str) {
	  var adicionar = 6 - str.length;
	  for (var i = 0; i < adicionar; i++) str = '0' + str;
	  return str.slice(0, 5) + '-' + str.slice(-1);
	}



function ltrim(valor, strTrim) {
	while (valor.substring(0, 1) == strTrim) {
		valor = valor.substring(1, valor.length);
	}
	return valor;
}



function addSeparador(num, separador) {
	numArr = new String(num).split('').reverse();
	for ( var i = 3; i < numArr.length; i += 3) {
		numArr[i] += separador;
	}
	return numArr.reverse().join('');
}

function maskFillZero(){

        var ndigitados = $("input.maskFillZero").val().length;
        var zeros = (10 - ndigitados);
        var novovalor = '';
        
        for(i=0; i<zeros; i++){
          
          novovalor += '0';
        }
        novovalor += $("input.maskFillZero").val().toString();
        
         $("input.maskFillZero").val(novovalor);
}

function maskFillZeroFind(){

    var ndigitados = $("input.maskFillZero").val().length;
    var zeros = (10 - ndigitados);
    var novovalor = '';
    if($("input.maskFillZero").val().length > 0){
    for(i=0; i<zeros; i++){
      
      novovalor += '0';
     }
    }
    novovalor += $("input.maskFillZero").val().toString();
    
     $("input.maskFillZero").val(novovalor);
}


    
function scrollDown(){
	window.scrollBy(0,300);
	}
