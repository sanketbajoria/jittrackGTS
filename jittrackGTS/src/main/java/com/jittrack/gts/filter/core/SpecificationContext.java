package com.jittrack.gts.filter.core;

import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import java.util.HashMap;
import java.util.Map;


public class SpecificationContext {

    public Map<String, Path> relationPathCache = new HashMap<String, Path>();
    public Map<String, From> relationJoinCache = new HashMap<String, From>();

}
