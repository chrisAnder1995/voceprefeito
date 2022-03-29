import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';

@Component({
  selector: 'di-support',
  templateUrl: './support.component.html',
  styleUrls: ['./support.component.css']
})
export class SupportComponent implements OnInit {
  contactName: string = ""
  contactMail: string = ""
  contactSubject: string = ""
  contactMessage: string = ""

  constructor(private appService: AppService) { }

  ngOnInit() {
    this.appService.setTitle('Support')
    this.appService.setMap(['Support'])
  }

}
