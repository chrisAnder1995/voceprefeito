import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'di-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit {
  @Input() title: string
  @Input() modalId: string
  @Input() footer: boolean = true

  constructor() { }

  ngOnInit() {
  }
}
