import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';

@Component({
  selector: 'di-index',
  templateUrl: './index.component.html'
})
export class IndexComponent implements OnInit {

  constructor(private appService: AppService) { }

  ngOnInit() {
    this.appService.setTitle('Você Prefeito')
    this.appService.setMap(['Index'])
  }

}
