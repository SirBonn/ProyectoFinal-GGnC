import { Component } from '@angular/core';
import { FilesService } from '../../../services/files.service';

@Component({
  selector: 'app-files-mng',
  templateUrl: './files-mng.component.html',
  styleUrls: ['./files-mng.component.css'],
})
export class FilesMngComponent {
  constructor(private filesService: FilesService) {}

  selectedFile!: File;
  fileStatus: String = "enEspera";


  uploadJSONfile() {
    this.fileStatus = "subiendo";
    this.filesService.uploadJSONfile(this.selectedFile).subscribe({
      next: () => {
        this.fileStatus = "subido";
      },
      error: (error) => {
        this.fileStatus = "error";
        console.log(error.error);
      }
    });
  }

  processFile(event: Event): void {
    let files = (event.target as HTMLInputElement).files;
    if (files != null) {
      this.selectedFile = files[0];
    }
  }

}
