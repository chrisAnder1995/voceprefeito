import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'di-login-footer',
  templateUrl: './login-footer.component.html',
  styleUrls: ['./login-footer.component.css']
})
export class LoginFooterComponent implements OnInit {
  @Input() showSupportLink: boolean

  constructor() { }

  ngOnInit() {
  }

}
