import com.politechnika.Beer;
import com.politechnika.BeerRepository;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

public class BeerRepositoryTest {

    private BeerRepository mageRepo;

    @Before
    public void setup() {
        mageRepo = new BeerRepository();
    }

    @Test
    public void delete_ElementNotInCollection_ExceptionThrown() {
        String nameNotInRepo = "Veigar";

        Throwable thrown = catchThrowable(() -> {
            mageRepo.delete(nameNotInRepo);
        });
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Beer not found");

    }

    @Test
    public void find_ElementNotInCollection_EmptyOptional() {
        Optional<Beer> res = mageRepo.find("Veigar");

        assert res.isEmpty() : "should return Optional.empty()";
    }

    @Test
    public void find_ElementInCollection_OptionalWithMage() {
        String name = "Veigar";
        mageRepo.save(new Beer(name, 12.5f));

        Optional<Beer> res = mageRepo.find(name);

        assertThat(res.get().getNazwa()).isEqualTo(name);

    }

    @Test
    public void save_ElementAlreadyInCollection_OptionalWithMage() {
        String name = "Veigar";
        mageRepo.save(new Beer(name, 12.5f));

        Throwable thrown = catchThrowable(() -> {
            mageRepo.save(new Beer(name, 12.5f));
        });

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Already in collection");

    }

}
