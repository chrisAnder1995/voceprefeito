import { Directive, HostListener, HostBinding, Input } from '@angular/core';
import { NgControl } from '@angular/forms';

@Directive({
  selector: '[CEPMask]',
})
export class CEPMaskDirective {

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
  
  onInputChange(event, backspace) {
    let newVal = event.replace(/\D/g, '');
    if (newVal.length === 0) {
      newVal = '';
    } else if (newVal.length <= 5) {
      newVal = newVal.replace(/^(\d{0,5})/, '$1');
    } else if (newVal.length <= 8) {
      newVal = newVal.replace(/^(\d{0,5})(\d{0,3})/, '$1-$2');
    } else {
      newVal = newVal.substring(0, 8);
      newVal = newVal.replace(/^(\d{0,5})(\d{0,3})/, '$1-$2');
    }
    this.ngControl.valueAccessor.writeValue(newVal);
  }
}
