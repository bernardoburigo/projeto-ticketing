import { Home, Calendar, Ticket, Users, LogOut } from 'lucide-react';
import { Link } from 'react-router-dom';

const Sidebar = () => {
  return (
    <div className="h-screen w-64 bg-gray-900 text-white flex flex-col">
      <div className="text-2xl font-bold p-6 border-b border-gray-700">
        Ticketing
      </div>
      <nav className="flex-1 p-4">
        <ul className="space-y-4">
          <Link to='/' className="flex items-center gap-3 hover:text-blue-400 cursor-pointer">
            <Home size={20} />
            Dashboard
          </Link>
          <Link to='/eventos' className="flex items-center gap-3 hover:text-blue-400 cursor-pointer">
            <Calendar size={20} />
            Eventos
          </Link>
          <Link to='/relatorios' className="flex items-center gap-3 hover:text-blue-400 cursor-pointer">
            <Ticket size={20} />
            Relatórios
          </Link>
          <Link to='/promotores' className="flex items-center gap-3 hover:text-blue-400 cursor-pointer">
            <Users size={20} />
            Promotores
          </Link>
        </ul>
      </nav>
      <div className="p-4 border-t border-gray-700 flex items-center gap-3 cursor-pointer hover:text-red-400">
        <LogOut size={20} />
        Sair
      </div>
    </div>
  );
};

export default Sidebar;