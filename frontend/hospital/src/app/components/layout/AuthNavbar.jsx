import { NavLink } from "react-router-dom"
import logo from "../../../assets/images/hospital-building.png"

/**
 * Minimal navbar used only on auth pages (login / register)
 * so the main site navbar remains for public pages like Home, About, etc.
 */
export const AuthNavbar = () => {
    return (
        <header className="w-full bg-blue-500 shadow-md">
            <div className="max-w-6xl mx-auto flex items-center justify-between px-4 h-16">
                <NavLink
                    to="/"
                    className="flex items-center gap-2 text-white font-semibold text-lg"
                >
                    <img
                        className="h-10 w-auto"
                        src={logo}
                        alt="Hospital Logo"
                    />
                    <span className="hidden sm:inline">Hospital ENSAS</span>
                </NavLink>

                <NavLink
                    to="/"
                    className="text-sm text-white/90 hover:text-white underline"
                >
                    Back to home
                </NavLink>
            </div>
        </header>
    )
}

export default AuthNavbar

