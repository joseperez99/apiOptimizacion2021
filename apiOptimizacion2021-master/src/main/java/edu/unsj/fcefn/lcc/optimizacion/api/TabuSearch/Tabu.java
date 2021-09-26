package edu.unsj.fcefn.lcc.optimizacion.api.TabuSearch;

import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.FrameDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.model.domain.StopDTO;
import edu.unsj.fcefn.lcc.optimizacion.api.services.FramesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tabu {

    private float precio_alcanzar;
    private float precio_inicial;
    private int[] tablaTabu;
    private FrameDTO[] frames;
    private StopDTO[] stops;

    @Autowired
    FramesService framesService;

    private void init(List<StopDTO> stops)
    {
        this.stops = stops.toArray(new StopDTO[stops.size()]);
        precio_alcanzar = this.stops.length * MediaPrecio();
        frames = CalcularFrames();
        precio_inicial = CalcularPrecio();
        tablaTabu = new int[this.stops.length - 1];

        Tabu();
    }

    public float MediaPrecio()
    {
        float precio = 0f;

        List<FrameDTO> frameDTO = framesService.findAll();

        for(FrameDTO frame : frameDTO)
        {
            precio += frame.getPrice();
        }
        return (precio / frameDTO.size());
    }

    public FrameDTO[] CalcularFrames()
    {
        float precio = 0f;
        List<FrameDTO> frameList = new ArrayList<>();

        for(int i = 0; i < stops.length - 2; i++)
        {
            frameList.add(MenorPrecio(i));
        }
        return (FrameDTO[]) frameList.toArray();
    }

    public FrameDTO MenorPrecio(int i)
    {
        List<FrameDTO> frameListAux = framesService.findByIdDeparturesStopAndIdArrivalStop(stops[i].getId(), stops[i + 1].getId());
        FrameDTO frameMenorPrecio = new FrameDTO();
        frameMenorPrecio.setPrice(9999999f);

        for(int j = 0; j < frameListAux.size() - 1; j++)
        {
            if(frameMenorPrecio.getPrice() > frameListAux.get(j).getPrice())
            {
                frameMenorPrecio = frameListAux.get(j);
            }
        }
        return frameMenorPrecio;
    }

    public float CalcularPrecio()
    {
        float precio = 0f;

        for (FrameDTO frame : frames)
        {
            precio += frame.getPrice();
        }

        return precio;
    }

    public StopDTO[] Tabu()
    {
        float precio = precio_inicial;
        Random r = new Random();
        int i, j = 0;

        while(precio > precio_alcanzar)
        {
            i = r.nextInt(stops.length);

            if (tablaTabu[i] == 0)
            {
                precio = precio -+ frames[i - 1].getPrice() - frames[i + 1].getPrice();

                StopDTO aux = stops[i];
                stops[i] = stops[i - 1];
                stops[i - 1] = aux;

                frames[i - 1] = MenorPrecio(i - 1);
                frames[i + 1] = MenorPrecio(i + 1);

                precio = precio + frames[i - 1].getPrice() + frames[i + 1].getPrice();

                NoCero();

                tablaTabu[i] = 20;
            }
            else
            {
                j++;
            }
            if (j > 10) //Si todos los espacios son Tabú entonces se permite al menos tabú trabajar
            {
                i = EncontrarMinimo();

                precio = precio -+ frames[i - 1].getPrice() - frames[i + 1].getPrice();

                StopDTO aux = stops[i];
                stops[i] = stops[i - 1];
                stops[i - 1] = aux;

                frames[i - 1] = MenorPrecio(i - 1);
                frames[i + 1] = MenorPrecio(i + 1);

                precio = precio + frames[i - 1].getPrice() + frames[i + 1].getPrice();

                NoCero();
            }
        }

        return stops;
    }

    public void NoCero() //se utiliza cada turno para reducir los valores en la Tabla Tabú
    {
        int n = stops.length;

        for (int i = 0; i < n - 1; i++)
        {
            if (tablaTabu[i] > 0)
            {
                tablaTabu[i] -= 1;
            }
        }
    }

    public int EncontrarMinimo()
    {
        int min = -1;
        int tabumin = 99;

        for (int i = 1; i < stops.length - 2; i++)
        {
            if (tablaTabu[i] < tabumin)
            {
                tabumin = tablaTabu[i];
                min = i;
            }
        }
        return min;
    }

}
