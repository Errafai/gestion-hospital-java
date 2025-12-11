export class User {
  constructor(data = {}) {
    this.id = data.id || null;
    this.firstName = data.firstName || "";
    this.lastName = data.lastName || "";
    this.email = data.email || "";
    this.role = data.role || "user";
    this.phone = data.phone || "";
    this.profileImage = data.profileImage || null;
    this.createdAt = data.createdAt || new Date();
  }
}

export default User;
