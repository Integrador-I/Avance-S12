"use client";

import { useEffect, useState } from "react";
import axios from "axios";
import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
import { Cliente } from "./typeCliente";

interface Props {
  clientes: Cliente[];
  setClientes: React.Dispatch<React.SetStateAction<Cliente[]>>;
}

export const TableCliente = ({ clientes, setClientes }: Props) => {
  const [editCliente, setEditCliente] = useState<Cliente | null>(null);
  const [editData, setEditData] = useState<Omit<Cliente, "clienteId" | "fechaRegistro">>({
    nombre: "",
    segundoNombre: "",
    apellido: "",
    apellidoMaterno: "",
    documentoIdentidad: "",
    telefono: "",
    correo: "",
    direccion: "",
    descripcion: "",
    password: "",
  });

  useEffect(() => {
    const fetchClientes = async () => {
      try {
        const res = await axios.get("http://localhost:8080/clientes");
        setClientes(res.data);
      } catch (err) {
        console.error("Error al obtener clientes", err);
      }
    };

    fetchClientes();
  }, [setClientes]);

  const handleDelete = async (clienteId: number) => {
    try {
      await axios.delete(`http://localhost:8080/clientes/${clienteId}`);
      setClientes(prev => prev.filter(cli => cli.clienteId !== clienteId));
    } catch (err) {
      console.error("Error al eliminar cliente", err);
    }
  };

  const openEditModal = (cliente: Cliente) => {
    setEditCliente(cliente);
    const { clienteId, fechaRegistro, ...rest } = cliente;
    setEditData(rest);
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setEditData(prev => ({ ...prev, [name]: value }));
  };

  const handleEditSubmit = async () => {
    if (!editCliente) return;

    try {
      await axios.put(`http://localhost:8080/clientes/${editCliente.clienteId}`, editData);
      setClientes(prev =>
        prev.map(cli =>
          cli.clienteId === editCliente.clienteId ? { ...cli, ...editData } : cli
        )
      );
      setEditCliente(null);
    } catch (err) {
      console.error("Error al actualizar cliente", err);
    }
  };

  return (
    <div className="relative shadow-md sm:rounded-lg max-w-full">
      <table className="w-full text-sm text-left text-gray-500">
        <thead className="text-xs text-gray-700 uppercase bg-gray-100">
          <tr>
            <th className="px-4 py-2">ID</th>
            <th className="px-4 py-2">Nombre</th>
            <th className="px-4 py-2">Segundo Nombre</th>
            <th className="px-4 py-2">Apellido</th>
            <th className="px-4 py-2">Apellido Materno</th>
            <th className="px-4 py-2">DNI</th>
            <th className="px-4 py-2">Correo</th>
            <th className="px-4 py-2">Teléfono</th>
            <th className="px-4 py-2">Dirección</th>
            <th className="px-4 py-2">Descripción</th>
            <th className="px-4 py-2">Contraseña</th>
            <th className="px-4 py-2">Acciones</th>
          </tr>
        </thead>
        <tbody>
          {clientes.map(cli => (
            <tr key={cli.clienteId} className="bg-white border-b hover:bg-gray-50">
              <td className="px-4 py-2">{cli.clienteId}</td>
              <td className="px-4 py-2">{cli.nombre}</td>
              <td className="px-4 py-2">{cli.segundoNombre}</td>
              <td className="px-4 py-2">{cli.apellido}</td>
              <td className="px-4 py-2">{cli.apellidoMaterno}</td>
              <td className="px-4 py-2">{cli.documentoIdentidad}</td>
              <td className="px-4 py-2">{cli.correo}</td>
              <td className="px-4 py-2">{cli.telefono}</td>
              <td className="px-4 py-2">{cli.direccion}</td>
              <td className="px-4 py-2">{cli.descripcion}</td>
              <td className="px-4 py-2">{cli.password}</td>
              <td className="px-4 py-2 space-x-2">
                <button onClick={() => openEditModal(cli)} className="text-blue-600 hover:underline">Editar</button>
                <button onClick={() => handleDelete(cli.clienteId)} className="text-red-600 hover:underline">Eliminar</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <Dialog open={!!editCliente} onOpenChange={() => setEditCliente(null)}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>Editar Cliente</DialogTitle>
          </DialogHeader>
          <div className="space-y-3">
            {Object.entries(editData).map(([key, value]) => (
              <Input key={key} name={key} value={value} onChange={handleChange} placeholder={key} />
            ))}
          </div>
          <DialogFooter>
            <Button onClick={handleEditSubmit}>Guardar</Button>
            <Button variant="outline" onClick={() => setEditCliente(null)}>Cancelar</Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
};
