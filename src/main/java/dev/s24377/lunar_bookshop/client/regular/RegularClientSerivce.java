package dev.s24377.lunar_bookshop.client.regular;


import dev.s24377.lunar_bookshop.enums.GENDER;

import java.util.Optional;

public interface RegularClientSerivce {

    RegularClientDTO registerClient(String name, String surname, GENDER gender);
}
