import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable, Injector } from "@angular/core";
import { Observable } from "rxjs";
import { __param } from "tslib";
import { ComicDTO } from "../dto/comic-dto";
import { AbstractService } from "./template.service";

@Injectable({
  providedIn: "root",
})

export class GestionarComicService extends AbstractService {
    constructor(private injector: Injector, private httpClient: HttpClient) {
      super(injector);
    }

    public crearComic(idComic: number, cantidad : number): Observable<any> {
        return this.httpClient.post(
        "http://localhost:8085/semillero-servicios/rest/gestionarCompraComic/comprarComic",
        idComic
        );
    }
}