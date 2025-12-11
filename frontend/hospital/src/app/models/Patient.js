export class Patient {
  constructor(data = {}) {
    this.id = data.id || null;
    this.firstName = data.firstName || "";
    this.lastName = data.lastName || "";
    this.email = data.email || "";
    this.phone = data.phone || "";
    this.dateOfBirth = data.dateOfBirth || "";
    this.gender = data.gender || "";
    this.address = data.address || "";
    this.createdAt = data.createdAt || new Date();
  }
}

export default Patient;
