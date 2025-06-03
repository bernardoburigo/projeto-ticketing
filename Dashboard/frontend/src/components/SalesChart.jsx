import { 
  LineChart, 
  Line, 
  XAxis, 
  YAxis, 
  CartesianGrid, 
  Tooltip, 
  ResponsiveContainer 
} from 'recharts';

const data = [
  { name: 'Jan', vendas: 4000 },
  { name: 'Fev', vendas: 3000 },
  { name: 'Mar', vendas: 5000 },
  { name: 'Abr', vendas: 4780 },
  { name: 'Mai', vendas: 5890 },
  { name: 'Jun', vendas: 4390 },
];

const SalesChart = () => {
  return (
    <div className="bg-white p-6 rounded-2xl shadow w-full">
      <h2 className="text-xl font-semibold mb-6 flex items-center gap-2">
        📈 Vendas Mensais
      </h2>
      <ResponsiveContainer width="100%" height={300}>
        <LineChart data={data}>
          <CartesianGrid strokeDasharray="3 3" stroke="#e5e7eb" />
          <XAxis dataKey="name" stroke="#6b7280" />
          <YAxis stroke="#6b7280" />
          <Tooltip 
            contentStyle={{ backgroundColor: 'white', borderRadius: '8px', borderColor: '#e5e7eb' }} 
            labelStyle={{ color: '#374151' }} 
          />
          <Line 
            type="monotone" 
            dataKey="vendas" 
            stroke="#2563eb" 
            strokeWidth={3} 
            dot={{ r: 5 }} 
            activeDot={{ r: 8 }}
          />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
};

export default SalesChart;
