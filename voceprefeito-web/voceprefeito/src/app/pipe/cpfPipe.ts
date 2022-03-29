import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Pipe({
  name: 'cpfPipe',
})
export class CpfPipe implements PipeTransform {
  
  constructor(){}

  transform(text) {
    if (text == undefined){
      return undefined
    }
    return text.replace(/^(\d{0,3})(\d{0,3})(\d{0,3})(\d{0,2})/, '$1.$2.$3-$4');
  }
}
