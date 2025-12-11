export class DossierMedical {
  constructor(data = {}) {
    this.id = data.id || null;
    this.patientId = data.patientId || null;
    this.consultations = data.consultations || [];
    this.prescriptions = data.prescriptions || [];
    this.medicalHistory = data.medicalHistory || "";
    this.allergies = data.allergies || [];
    this.createdAt = data.createdAt || new Date();
    this.updatedAt = data.updatedAt || new Date();
  }
}

export default DossierMedical;
