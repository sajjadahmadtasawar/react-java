package no.ssb.sivadmin.models

import org.apache.commons.lang3.builder.HashCodeBuilder

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = 'bruker_rolle')
class BrukerRolle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name="BRUKER_ID", referencedColumnName = "ID")
    Bruker bruker

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name="ROLLE_ID", referencedColumnName = "ID")
    Rolle rolle
}
