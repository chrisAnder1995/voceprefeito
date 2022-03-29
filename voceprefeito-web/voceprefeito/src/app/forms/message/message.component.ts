import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'di-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {
  @Input() message: string
  @Input() errorMessage: boolean = false
  @Input() successMessage: boolean = false
  @Input() warningMessage: boolean = false
  @Input() infoMessage: boolean = false

  constructor() { }

  ngOnInit() {
  }

}
