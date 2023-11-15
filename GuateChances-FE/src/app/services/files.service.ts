import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class FilesService {
  constructor(
    private httpClient: HttpClient,
  ) {}

  public uploadJSONfile(file: File):Observable<void> {
    let formData = new FormData();
    formData.append('JSONfile', file);
    return this.httpClient.post<void>('http://localhost:8080/guatechances-api/controller/files', formData);
  }

  public uploadPDFfile(file: File, idCode:String):Observable<void> {
    let formData = new FormData();
    formData.append('PDFfile', file);
    return this.httpClient.post<void>('http://localhost:8080/guatechances-api/controller/usr/seekers?action=savePDF&idCode='+idCode, formData);
  }
}
