import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Pipe({
  name: 'cepPipe',
})
export class CepPipe implements PipeTransform {
  
  constructor(){}

  transform(text) {
    if (text == undefined){
      return undefined
    }
    return text.replace(/^(\d{0,5})(\d{0,3})/, '$1-$2');
  }
}
