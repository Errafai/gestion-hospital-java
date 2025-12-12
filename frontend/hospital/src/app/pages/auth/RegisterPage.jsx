import React, { useState } from "react";
import { Activity } from "lucide-react";
import { NavLink } from "react-router-dom";

export const RegisterPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [role, setRole] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    /*
     * TODO backend registration wiring:
     * - Call POST /api/auth/register with { name, email, password, role }.
     * - Expect tokens and user role in response; store tokens (localStorage/cookie) and set Authorization header for api client.
     * - Redirect after success (confirm mapping): admin -> /dashboard, doctor -> /appointments, nurse -> /patients.
     * - Show a user-friendly error on 4xx/5xx and keep the form values so the user can retry.
     * - Consider a loading state to disable the button while the request is in flight.
     */
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-500 to-purple-700 flex items-center justify-center p-4">
      <div className="bg-white rounded-2xl shadow-2xl p-8 w-full max-w-md">
        <div className="text-center mb-8">
          <div className="inline-block p-4 bg-blue-100 rounded-full mb-4">
            <Activity className="w-10 h-10 text-blue-500" />
          </div>
          <h2 className="text-3xl font-bold text-gray-800">Welcome To Our Application</h2>
          <p className="text-gray-600 mt-2">Please register to have an account</p>
        </div>
        <div>
          <form onSubmit={handleSubmit} className="space-y-6">
            {/* userName */}

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                userName
              </label>
              <input
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="Your name"
                className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
            
            {/* email */}

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Email
              </label>
              <input
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="exemple@email.com"
                className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>

            {/* password */}

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Password
              </label>
              <input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="••••••••"
                className="w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>

            {/* role */}

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Role
              </label>
              <select name="role" id="role" value={role} onChange={(e) => setRole(e.target.value)} className="w-full py-3 border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500 rounded-lg">
                <option value="admin">Admin</option>
                <option value="doctor">Doctor</option>
                <option value="nurse">Nurse</option>
              </select>
            </div>

            {/* the remember me and forgot password */}

            <div className="flex items-center justify-between">
              <label className="flex items-center">
                <input type="checkbox" className="mr-2" />
                <span className="text-sm text-gray-600">Remember me</span>
              </label>
            </div>

            <button className="w-full bg-blue-600 text-white py-3 rounded-lg font-semibold hover:bg-blue-700 transition-colors">
              Register In
            </button>
          </form>

          <p className="text-center text-sm text-gray-600 mt-6">
            Already have an account?{" "}
            <NavLink to="/login" className="text-blue-600 hover:underline">
              Sign In
            </NavLink>
          </p>
        </div>
      </div>
    </div>
  );
};

export default RegisterPage;
