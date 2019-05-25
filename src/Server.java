import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		
		Socket s = null;
		ServerSocket ss = new ServerSocket(5400);
		
		while (true)
		{
			try
			{
				// el ServerSocket me da el Socket
				s = ss.accept();
				// informacion en la consola
				System.out.println("Se conectaron desde la IP: " +s.getInetAddress());
				// enmascaro la entrada y salida de bytes
				ois = new ObjectInputStream( s.getInputStream() );
				oos = new ObjectOutputStream( s.getOutputStream() );
				// leo el nombre que envia el cliente
				String nom = (String)ois.readObject();
				String[] numeros = nom.split(",");
				
				Integer resultado = 1;
				for (int i = 0; i < numeros.length; i++)
				{
					Integer n = Integer.parseInt(numeros[i]);
					System.out.println("" + n);
					resultado *= n;
				}
				
				// armo el saludo personalizado que le quiero enviar
				String saludo = "Resultado: "+resultado;
				
				// envio el saludo al cliente
				oos.writeObject(saludo);
				System.out.println("Saludo enviado...");
				
				
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			} finally {
				if( oos !=null ) oos.close();
				if( ois !=null ) ois.close();
				if( s != null ) s.close();
				System.out.println("Conexion cerrada!");
			}
		}
	}

}
