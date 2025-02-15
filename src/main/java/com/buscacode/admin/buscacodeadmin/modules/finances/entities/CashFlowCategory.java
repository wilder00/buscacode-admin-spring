package com.buscacode.admin.buscacodeadmin.modules.finances.entities;

import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cash_flow_categories")
@FilterDef(name = "enabledCashFlowCategory", parameters = @ParamDef(name = "enabled", type = Boolean.class))
public class CashFlowCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String icon;

  private String color;

  private Integer order;

  @ManyToOne
  @JoinColumn(name = "category_father_id", referencedColumnName = "id", insertable = true, updatable = true)
  @JsonIgnoreProperties(value = { "subCategories", "categoryFather" })
  private CashFlowCategory categoryFather;

  @OneToMany(mappedBy = "categoryFather", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
  @JsonIgnoreProperties(value = { "categoryFather", "subCategories" })
  @Filter(name = "enabledCashFlowCategory", condition = "enabled = :enabled")
  @Fetch(FetchMode.SUBSELECT)
  private List<CashFlowCategory> subCategories;

  private Boolean enabled;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  public CashFlowCategory getCategoryFather() {
    return categoryFather;
  }

  public void setCategoryFather(CashFlowCategory categoryFather) {
    this.categoryFather = categoryFather;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public List<CashFlowCategory> getSubCategories() {
    return subCategories;
  }

  public void setSubCategories(List<CashFlowCategory> subCategories) {
    this.subCategories = subCategories;
  }

}
