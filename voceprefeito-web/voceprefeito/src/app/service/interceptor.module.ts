import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';


@Injectable()

export class HttpsRequestInterceptor implements HttpInterceptor {

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const idToken = localStorage.getItem("token")

        if (idToken) {
            let cloned = req.clone({
                headers: req.headers
                    .set("Authorization", "Bearer " + idToken)
            });

            return next.handle(cloned);
        }
        else {
            return next.handle(req);
        }
    }
}
