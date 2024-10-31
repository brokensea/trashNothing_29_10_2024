import { Link, Outlet } from 'react-router-dom';
export default function Header() {
    return (
        <div>
            <header className="bg-gray-800 text-white p-4">
                <nav className="space-x-4">
                    <Link to="/" className="hover:underline">Startseite</Link>
                    <Link to="/signup" className="hover:underline">Registrieren</Link>
                    <Link to="/login" className="hover:underline">Anmelden</Link> {/* 添加登录链接 */}
                    <Link to="/initializeTeam" className="hover:underline">Team einrichten</Link>
                    <Link to="/createMatch" className="hover:underline">Spiel erstellen</Link>
                </nav>
            </header>
            {/* <main className="p-4">
                <Outlet />
            </main> */}
        </div>
    );
}
