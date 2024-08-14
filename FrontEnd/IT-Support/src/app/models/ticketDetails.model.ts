

export interface TicketDetails {
  id: number;
  creationDate: string;
  description: string;
  status: string;
  user: {
    id: number;
    username: string;
    email: string;
    role: string;
  };
  fault: {
    id: number;
    description: string;
  };
  equipment: {
    id: number;
    imageUrl: string;
    equipmentName: string;
    purchaseDate: string;
    assetValue: string;
    serialNumber: string;
  };
  technician: {
    id: number;
    username: string;
    email: string;
    role: string;
  };
}
