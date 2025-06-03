import Sidebar from '../components/Sidebar';
import Topbar from '../components/Topbar';
import SalesChart from '../components/SalesChart';
import EventsTable from '../components/EventsTable';

const Dashboard = () => {
  return (
    <div className="flex">
      <Sidebar />
      <div className="flex-1 flex flex-col">
        <Topbar />
        <main className="p-6 bg-gray-100 min-h-screen">
          <h1 className="text-2xl font-bold mb-4">Visão Geral</h1>

          <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-6">
            <div className="bg-white rounded-xl p-6 shadow">🗓️ Eventos ativos: 5</div>
            <div className="bg-white rounded-xl p-6 shadow">🎟️ Ingressos vendidos: 1200</div>
            <div className="bg-white rounded-xl p-6 shadow">💰 Receita total: R$ 48.000</div>
          </div>

          <div className="flex flex-col gap-6">
            <SalesChart />
            <EventsTable />
          </div>
        </main>
      </div>
    </div>
  );
};

export default Dashboard;
