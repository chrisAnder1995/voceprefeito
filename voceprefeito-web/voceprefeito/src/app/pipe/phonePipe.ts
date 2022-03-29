import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Pipe({
  name: 'phonePipe',
})
export class PhonePipe implements PipeTransform {
  
  constructor(){}

  transform(text) {
    if (text == undefined){
      return undefined
    }
    return text.replace(/^(\d{0,2})(\d{0,5})(\d{0,4})/, '($1) $2-$3');
  }
}
