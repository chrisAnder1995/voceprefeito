import { Injectable, Inject } from "@angular/core";
import { BehaviorSubject } from "rxjs";
import {LOCAL_STORAGE, WebStorageService} from 'angular-webstorage-service'
import { Usuario } from "./model/Usuario.model";

@Injectable()
export class AppService {
  
  title = new BehaviorSubject<string>('')
  map = new BehaviorSubject<string[]>([])
  usuario = new Usuario()

  constructor(@Inject(LOCAL_STORAGE) private storage: WebStorageService) {

  }

  setTitle(title: string) {
    this.title.next(title)
  }

  setMap(map: string[]) {
    this.map.next(map)
  }

  saveInLocal(key, val): void {
    this.storage.set(key, val);
  }

  removeInLocal(key) {
    this.storage.remove(key)
  }
  
  getLoggedUser() : Usuario{
    let user : Usuario = JSON.parse(this.getFromLocal('usuario'))
    if (user){
      return user
    }
    return undefined
  }

  getFromLocal(key): string {
    let value = this.storage.get(key);
    return value
  }
}
