package no.ssb.sivadmin.models

import no.ssb.sivadmin.enums.AvtaleType

import javax.persistence.*

@Entity
class Avtale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @Enumerated(EnumType.STRING)
    AvtaleType avtaleType

    Date dateStart
    String timeStart
    Date dateEnd
    String timeEnd
    String weekDays
    String whoMade
    String avtaleMelding
}
