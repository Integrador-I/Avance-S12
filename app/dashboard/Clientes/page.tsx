"use client";

import { FormCliente } from "@/components/Dashboard/clientes/formCliente_TEMP";
import { TableCliente } from "@/components/Dashboard/clientes/tableCliente_TEMP";
import { useState } from "react";
import { Cliente } from "@/components/Dashboard/clientes/typeCliente";

export default function Clientes() {
  const [clientes, setClientes] = useState<Cliente[]>([]);

  return (
    <div className="min-h-screen px-2 py-8 bg-white">
      <div className="flex flex-col lg:flex-row justify-start items-start gap-8">
        <div className="flex flex-col w-full max-w-md">
          <h1 className="text-4xl font-semibold mb-8">Administración de Clientes</h1>
          <div className="p-6 rounded-lg shadow-md">
            <FormCliente setClientes={setClientes} />
          </div>
        </div>
        <div className="w-full flex-1 max-w-4xl pt-19">
          <TableCliente clientes={clientes} setClientes={setClientes} />
        </div>
      </div>
    </div>
  );
}
