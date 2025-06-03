const Topbar = () => {
  return (
    <div className="h-16 bg-white flex items-center justify-between px-6 border-b">
      <div className="text-xl font-semibold">Dashboard</div>
      <div className="flex items-center gap-4">
        <div className="w-8 h-8 rounded-full bg-gray-300"></div>
        <div>
          <p className="text-sm font-medium">Bernardo</p>
          <p className="text-xs text-gray-500">Admin</p>
        </div>
      </div>
    </div>
  );
};

export default Topbar;
