import { Directive, HostListener, HostBinding, Input } from '@angular/core';
import { NgControl } from '@angular/forms';

@Directive({
  selector: '[CPFMask]',
})
export class CPFMaskDirective {

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
    } else if (newVal.length <= 3) {
      newVal = newVal.replace(/^(\d{0,3})/, '$1');
    } else if (newVal.length <= 6) {
      newVal = newVal.replace(/^(\d{0,3})(\d{0,3})/, '$1.$2');
    } else if (newVal.length <= 9) {
      newVal = newVal.replace(/^(\d{0,3})(\d{0,3})(\d{0,3})/, '$1.$2.$3');
    } else if (newVal.length <= 11) {
      newVal = newVal.replace(/^(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,2})/, '$1.$2.$3-$4');
    } else {
      newVal = newVal.substring(0, 11);
      newVal = newVal.replace(/^(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,2})/, '$1.$2.$3-$4');
    }
    this.ngControl.valueAccessor.writeValue(newVal);
  }

  valida(cpf: string): boolean {
    debugger
    if (cpf == null) {
        return false;
    }
    if (cpf.length != 11) {
        return false;
    }
    if ((cpf == '00000000000') || (cpf == '11111111111') || (cpf == '22222222222') || (cpf == '33333333333') || (cpf == '44444444444') || (cpf == '55555555555') || (cpf == '66666666666') || (cpf == '77777777777') || (cpf == '88888888888') || (cpf == '99999999999')) {
        return false;
    }
    let numero: number = 0;
    let caracter: string = '';
    let numeros: string = '0123456789';
    let j: number = 10;
    let somatorio: number = 0;
    let resto: number = 0;
    let digito1: number = 0;
    let digito2: number = 0;
    let cpfAux: string = '';
    cpfAux = cpf.substring(0, 9);
    for (let i: number = 0; i < 9; i++) {
        caracter = cpfAux.charAt(i);
        if (numeros.search(caracter) == -1) {
            return false;
        }
        numero = Number(caracter);
        somatorio = somatorio + (numero * j);
        j--;
    }
    resto = somatorio % 11;
    digito1 = 11 - resto;
    if (digito1 > 9) {
        digito1 = 0;
    }
    j = 11;
    somatorio = 0;
    cpfAux = cpfAux + digito1;
    for (let i: number = 0; i < 10; i++) {
        caracter = cpfAux.charAt(i);
        numero = Number(caracter);
        somatorio = somatorio + (numero * j);
        j--;
    }
    resto = somatorio % 11;
    digito2 = 11 - resto;
    if (digito2 > 9) {
        digito2 = 0;
    }
    cpfAux = cpfAux + digito2;
    if (cpf != cpfAux) {
        return false;
    }
    else {
        return true;
    }
}
}
