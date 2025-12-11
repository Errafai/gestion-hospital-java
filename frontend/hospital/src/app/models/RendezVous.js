export class RendezVous {
  constructor(data = {}) {
    this.id = data.id || null;
    this.patientId = data.patientId || null;
    this.doctorId = data.doctorId || null;
    this.appointmentDate = data.appointmentDate || null;
    this.time = data.time || "";
    this.status = data.status || "scheduled";
    this.notes = data.notes || "";
    this.createdAt = data.createdAt || new Date();
  }
}

export default RendezVous;
