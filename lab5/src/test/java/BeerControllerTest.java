import com.politechnika.Beer;
import com.politechnika.BeerController;
import com.politechnika.BeerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BeerControllerTest {
    @Mock
    private BeerRepository beerRepository;

    private BeerController mageController;

    @Before
    public void setup() {
        beerRepository = Mockito.mock(BeerRepository.class);
        mageController = new BeerController(beerRepository);
    }

    @Test
    public void delete_existingObject_stringDone() {
        String name = "Test";
        doNothing().when(beerRepository).delete(name);

        assertThat(mageController.delete(name)).isEqualTo("done");
    }

    @Test
    public void delete_notExistingObject_stringNotFound() {
        String name = "Test";

        doThrow(IllegalArgumentException.class).when(beerRepository).delete(name);

        assertThat(mageController.delete(name)).isEqualTo("not found");
    }
    @Test
    public void find_notExistingObject_stringNotFound() {
        String name = "Test";

        doReturn(Optional.empty()).when(beerRepository).find(name);

        assertThat(mageController.find(name)).isEqualTo("not found");
    }
    @Test
    public void find_ElementInCollection_stringOfObject() {
        String name = "tester";
        Beer testBeer = new Beer(name, 12);

        doReturn(Optional.of(testBeer)).when(beerRepository).find(name);

        assertThat(mageController.find(name)).isEqualTo(testBeer.toString());
    }

    @Test
    public void save_ElementNotInCollection_stringDone() {
        String name = "tester";
        float procenty = 5.6f;
        doNothing().when(beerRepository).save(any());

        assertThat(mageController.save(name, procenty)).isEqualTo("done");
    }
    @Test
    public void save_ElementAlreadyInCollection_stringBadRequest() {
        String name = "tester";
        float procenty = 5.6f;
        doThrow(IllegalArgumentException.class).when(beerRepository).save(any());

        assertThat(mageController.save(name, procenty)).isEqualTo("bad request");
    }
}
