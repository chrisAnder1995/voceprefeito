import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';

@Component({
  selector: 'di-template',
  templateUrl: './template.component.html',
  styleUrls: ['./template.component.css']
})
export class TemplateComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    let windowHeight  = $('window').height() - 50
    windowHeight -=  $('footer').outerHeight()
    $('#contentWrapper').css('min-height', windowHeight+'px')
  }

}
