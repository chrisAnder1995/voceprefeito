import { Directive, HostListener, Input, HostBinding } from '@angular/core';
import { NgControl } from '@angular/forms';

@Directive({
  selector: '[appDateMask]',
})
export class DateMaskDirective {

  @Input('class')
  @HostBinding('class')
  elementClass = '';

  constructor(public ngControl: NgControl) { }

  @HostListener('ngModelChange', ['$event'])
  onModelChange(event) {
    this.onInputChange(event, false);
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

  valida(value) : boolean {
    this.onInputChange(value, true);

    let fail = false
    value = value.replace(/\D/g, '');
    if (value.length != 8){
      fail = true
    } else{
      let dia = +value.substring(0,2)
      let mes = +value.substring(2,4)
      let ano = +value.substring(4,8)

      if (mes <= 0 || mes > 12 || dia <= 0 || dia > 31 || ano < 1900 || ano > 2100){
        fail = true
      }

      if ([1,3,5,7,8,10,12].indexOf(mes) >= 0 && (dia <= 0 || dia > 31)){
        fail = true
      }
      if ([4,6,9,11].indexOf(mes) >= 0 && (dia <= 0 || dia > 30)){
        fail = true
      }

      // ANO BISSEXTO
      if ((ano % 400 == 0) || (ano % 4 == 0 && ano % 100 != 0)){
        if (mes == 2 && (dia <= 0 || dia > 29)){
          fail = true
        }
      } else{
        if (mes == 2 && (dia <= 0 || dia > 28)){
          fail = true
        }
      }
    }

    return !fail
  }

  @HostListener('keydown.backspace', ['$event'])
  keydownBackspace(event) {
    this.onInputChange(event.target.value, true);
  }
  
  onInputChange(event, backspace) {
    let newVal = event.replace(/\D/g, '');
    if (newVal.length === 0) {
      newVal = '';
    } else if (newVal.length <= 2) {
      newVal = newVal.replace(/^(\d{0,2})/, '$1');
    } else if (newVal.length <= 4) {
      newVal = newVal.replace(/^(\d{0,2})(\d{0,2})/, '$1/$2');
    } else if (newVal.length <= 8) {
      newVal = newVal.replace(/^(\d{0,2})(\d{0,2})(\d{0,4})/, '$1/$2/$3');
    } else {
      newVal = newVal.substring(0, 8);
      newVal = newVal.replace(/^(\d{0,2})(\d{0,2})(\d{0,4})/, '$1/$2/$3');
    }
    this.ngControl.valueAccessor.writeValue(newVal);
  }
}
