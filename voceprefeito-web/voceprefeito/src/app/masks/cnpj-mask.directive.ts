import { Directive, HostListener, HostBinding, Input } from '@angular/core';
import { NgControl } from '@angular/forms';

@Directive({
  selector: '[CNPJMask]',
})
export class CNPJMaskDirective {

  @Input('class')
  @HostBinding('class')
  elementClass = '';

  constructor(public ngControl: NgControl) { }
  

  @HostListener('ngModelChange', ['$event'])
  onModelChange(event) {
    this.onInputChange(event, false);
  }

  @HostListener('keydown.backspace', ['$event'])
  keydownBackspace(event) {
    this.onInputChange(event.target.value, true);
  }

  @HostListener('blur', ['$event'])
  blur(event) {
    let valor = event.target.value
    valor = valor.replace(/\D/g, '');
    if (valor != "" && !this.valida(valor)){
      this.elementClass += " has-error"
      this.ngControl.valueAccessor.writeValue("");
    } else{
      this.elementClass = this.elementClass.replace(" has-error","")
    }
  }
  
  onInputChange(event, backspace) {
    let newVal = event.replace(/\D/g, '');
    if (newVal.length === 0) {
      newVal = '';
    } else if (newVal.length <= 2) {
      newVal = newVal.replace(/^(\d{0,2})/, '$1');
    } else if (newVal.length <= 5) {
      newVal = newVal.replace(/^(\d{0,2})(\d{0,3})/, '$1.$2');
    } else if (newVal.length <= 8) {
      newVal = newVal.replace(/^(\d{0,2})(\d{0,3})(\d{0,3})/, '$1.$2.$3');
    } else if (newVal.length <= 12) {
      newVal = newVal.replace(/^(\d{0,2})(\d{0,3})(\d{0,3})(\d{0,4})/, '$1.$2.$3/$4');
    } else if (newVal.length <= 14) {
      newVal = newVal.replace(/^(\d{0,2})(\d{0,3})(\d{0,3})(\d{0,4})(\d{0,2})/, '$1.$2.$3/$4-$5');
    } else {
      newVal = newVal.substring(0, 14);
      newVal = newVal.replace(/^(\d{0,2})(\d{0,3})(\d{0,3})(\d{0,4})(\d{0,2})/, '$1.$2.$3/$4-$5');
    }
    this.ngControl.valueAccessor.writeValue(newVal);
  }

  valida(cnpj) : boolean {
 
    cnpj = cnpj.replace(/[^\d]+/g,'');
 
    if(cnpj == '') return false;
     
    if (cnpj.length != 14)
        return false;
 
    // Elimina CNPJs invalidos conhecidos
    if (cnpj == "00000000000000" || 
        cnpj == "11111111111111" || 
        cnpj == "22222222222222" || 
        cnpj == "33333333333333" || 
        cnpj == "44444444444444" || 
        cnpj == "55555555555555" || 
        cnpj == "66666666666666" || 
        cnpj == "77777777777777" || 
        cnpj == "88888888888888" || 
        cnpj == "99999999999999")
        return false;
         
    // Valida DVs
    let tamanho = cnpj.length - 2
    let numeros = cnpj.substring(0,tamanho);
    let digitos = cnpj.substring(tamanho);
    let soma = 0;
    let pos = tamanho - 7;
    for (let i = tamanho; i >= 1; i--) {
      soma += numeros.charAt(tamanho - i) * pos--;
      if (pos < 2)
            pos = 9;
    }
    let resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(0))
        return false;
         
    tamanho = tamanho + 1;
    numeros = cnpj.substring(0,tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (let i = tamanho; i >= 1; i--) {
      soma += numeros.charAt(tamanho - i) * pos--;
      if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(1))
          return false;
           
    return true;
    
  }
}
