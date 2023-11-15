import { Component, Input } from '@angular/core';
import { FilesService } from 'src/app/services/files.service';
import { User } from 'src/entities/User';

@Component({
  selector: 'app-info-request-seekers',
  templateUrl: './info-request-seekers.component.html',
  styleUrls: ['./info-request-seekers.component.css'],
})
export class InfoRequestSeekersComponent {
  @Input()
  isPDFfileUploaded!: boolean;
  @Input()
  seeker!: User
  selectedFile!: File;
  errorMessage: string = '';
  fileStatus: String = 'enEspera';

constructor(
  private filesService: FilesService
) {}

  uploadPDFfile() {
    this.fileStatus = 'subiendo';
    this.filesService
      .uploadPDFfile(this.selectedFile, this.seeker.idCode)
      .subscribe({
        next: () => {
          this.fileStatus = 'subido';
          console.log('Archivo subido exitosamente');
          alert('Archivo subido exitosamente');
          window.location.reload();
        },
        error: (error) => {
          this.errorMessage = error.error.error;
          this.fileStatus = 'error';
          console.log('error', error);
        },
      });
  }

  processFile(event: Event): void {
    let files = (event.target as HTMLInputElement).files;
    if (files != null) {
      this.selectedFile = files[0];
    }
  }

}
